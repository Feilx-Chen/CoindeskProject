package com.example.coindesk.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.example.coindesk.exception.ParamException;

@Aspect
@Component
public class ParamValidAspect {

	@Pointcut("execution(* com.example.coindesk.contoller..*(..))")
	public void webParam() {

	}

	@Before("webParam() &&args(..,bindingResult)")
	public void doBefore(JoinPoint joinPoint, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			StringBuffer errorBuffer = new StringBuffer();
			for (ObjectError error : bindingResult.getAllErrors()) {
				errorBuffer = errorBuffer.append(error.getDefaultMessage() + ";");
			}
			String msg = errorBuffer.toString().substring(0, errorBuffer.length() - 1);
			throw new ParamException(msg);
		}
	}
}