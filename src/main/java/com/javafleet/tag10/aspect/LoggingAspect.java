package com.javafleet.tag10.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * LoggingAspect - Aspect-Oriented Programming
 * 
 * Demonstriert:
 * - @Aspect (Tag 6)
 * - @Around Advice (Tag 6)
 * - Cross-Cutting Concerns (Tag 6)
 * - Execution Time Logging
 * 
 * AOP ermöglicht es, Logging-Logik zentral zu definieren
 * ohne den Business-Code zu verschmutzen!
 * 
 * @author Code Sentinel
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {
    
    /**
     * Loggt Ausführungszeit aller Service-Methoden
     * 
     * Pointcut: execution(* com.javafleet.tag10.service..*(..))
     * = Alle Methoden in allen Klassen im service Package
     */
    @Around("execution(* com.javafleet.tag10.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        // Methode ausführen
        Object result = joinPoint.proceed();
        
        long duration = System.currentTimeMillis() - startTime;
        
        log.info("⏱️ AOP: {} executed in {} ms", 
            joinPoint.getSignature().toShortString(), duration);
        
        return result;
    }
    
    /**
     * Loggt alle Controller-Aufrufe
     */
    @Around("execution(* com.javafleet.tag10.controller..*(..))")
    public Object logControllerCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("🎯 AOP: Controller call -> {}", 
            joinPoint.getSignature().toShortString());
        
        Object result = joinPoint.proceed();
        
        log.debug("✅ AOP: Controller call completed");
        
        return result;
    }
}
