package com.example.blog._core.aop;

import com.example.blog._core.error.ex.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Aspect
public class MyValidationAspect {

    // 행위 (@Before의 경우)
    // @Before : 이 경우 PostMapping 작동 직전에 실행됨
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)") // 포인트컷 자리
    public void validationCheck(JoinPoint jp) {
        Object[] args = jp.getArgs();

        for (Object arg : args) {
            // instanceof : 객체가 특정 클래스나 인터페이스의 인스턴스인지 확인
            // 객체 instanceof 클래스 또는 인터페이스
            if (arg instanceof Errors) { 
                Errors errors = (Errors) arg; // 다운캐스팅(Object -> Errors)

                if (errors.hasErrors()) { // 에러를 가졌는가?

                    String errMsg = errors.getFieldErrors().get(0).getField() + " : "
                            + errors.getFieldErrors().get(0).getDefaultMessage();
                    throw new Exception400(errMsg);
                }
            }
        }
    }

    // 행위 (@Around의 경우)
    // @Around : 이 경우 PostMapping 직전, 직후 실행됨
//    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)") // 포인트컷 자리
//    public Object validationCheck(ProceedingJoinPoint jp) throws Throwable {
//        Object[] args = jp.getArgs();
//
//        for (Object arg : args) {
//            if(arg instanceof Errors){
//                Errors errors = (Errors) arg;
//
//                if(errors.hasErrors()) {
//                    String errMsg = errors.getFieldErrors().get(0).getField() +" : "+ errors.getFieldErrors().get(0).getDefaultMessage();
//                    throw new Exception400(errMsg);
//                }
//            }
//        }
//        System.out.println("직전");
//        Object ob = jp.proceed();
//        System.out.println("직후");
//        return ob;
//    }
}