package edu.miu.cs544.aopaspect;

import java.util.NoSuchElementException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import edu.miu.cs544.exception.InvalidIdException;

@Aspect
@Configuration
public class ExceptionAdvice {
	
	@Around("execution(* edu.miu.cs544.controller.*.* (..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			return joinPoint.proceed();
		} catch (IllegalArgumentException | NoSuchElementException ex){
			throw new InvalidIdException("The provided identifying value does not match a record", ex);
		}		
	}
}
