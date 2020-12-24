package edu.miu.cs544.emailservice.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.cs544.emailservice.domain.Email;
import edu.miu.cs544.emailservice.domain.UpcomingFlightEmail;
import edu.miu.cs544.emailservice.job.EmailJob;
import edu.miu.cs544.emailservice.service.request.ScheduleEmailRequest;
import edu.miu.cs544.emailservice.service.response.ScheduleEmailResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


@Service
public class EmailSchedulingServiceImpl implements EmailSchedulingService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public ScheduleEmailResponse schedule(ScheduleEmailRequest request, Date date) throws SchedulerException {
    	date = rollback24Hrs(date);
        Email email = prepareEmail(request);
        JobDetail jobDetail = buildJobDetail(email);
        Trigger trigger = buildJobTrigger(jobDetail, date);
        scheduler.scheduleJob(jobDetail, trigger);

        return new ScheduleEmailResponse(true, jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Email Scheduled Successfully!");

    }
    
    private Email prepareEmail(ScheduleEmailRequest request) {
        String subject = new StringBuilder().append("Your upcoming flight with ")
                .append(request.getAirline()).append(" is in 24 hrs").toString();
        String body = new StringBuilder().append("Flight number ").append(request.getNumber())
                .append(" departs from ").append(request.getDepartureAirport())
                .append(" on ").append(request.getArrivalDate())
                .append(" and arrives at ").append(request.getArrivalAirport())
                .append(" on ").append(request.getArrivalDate())
                .append(". Please allow enough time to arrive at the airport at least an hour before the departure time")
                .append(". Have a safe journey, and thank you for flying with us.")
                .toString();

        return new UpcomingFlightEmail(request.getEmail(), subject, body);
    }

    private JobDetail buildJobDetail(Email email) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("email", email.getRecipientAddress());
        jobDataMap.put("subject", email.getSubject());
        jobDataMap.put("body", email.getBody());

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-job: upcoming flight")
                .withDescription("Sends email notifying passengers of their (first) flight in a reservation")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, Date startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-trigger")
                .withDescription("Send email trigger: scheduled 24hrs before flight")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }

	@Override
	public boolean isSchedulable(Date date) {
		Date emailTime = rollback24Hrs(date);
        return !emailTime.before(new Date());
    }
	
	private Date rollback24Hrs(Date date) {
		Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.HOUR, -19);
        return cal.getTime();
	}
}
