package dev.huai.aspects;

import dev.huai.models.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthAspect {

    //!getUserByCredential(..)
    @Around(" within(dev.huai.controllers.* ) ")
    public ResponseEntity<?> validateToken(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authToken = request.getHeader("Authorization");
        if(authToken == null && ("getUserByCredential"!= pjp.getSignature().getName()) && ("signUpNewCustomer"!= pjp.getSignature().getName()) && ("getAllProduct"!= pjp.getSignature().getName())){
            // prevent addNewBreed method from executing
            // return 401 to the client
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            // allow addNewBreed to execute normally
            //logger.info("token received");
            return (ResponseEntity<?>) pjp.proceed();
        }
    }
}
