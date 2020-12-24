package edu.miu.cs544.emailservice.service;

import org.quartz.SchedulerException;

import edu.miu.cs544.emailservice.service.request.ScheduleEmailRequest;
import edu.miu.cs544.emailservice.service.response.ScheduleEmailResponse;

import java.util.Date;

public interface EmailSchedulingService {

    ScheduleEmailResponse schedule(ScheduleEmailRequest request, Date dateTime) throws SchedulerException;
    boolean isSchedulable(Date date);
}
