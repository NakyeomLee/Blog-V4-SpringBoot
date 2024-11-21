package com.example.blog._core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
* AOP 실습해보기

1. HelloAspect 클래스 생성
2. @GetMapping 포인트컷
3. 포인트컷 직전에 동작 구성 (조인포인트 @Before)
4. 매개 변수에 Integer가 있으면 (int 다 Integer로 바꾸기)
5. [(해당번호)번아 안녕] ⇒ 출력하기
* */
@Component
@Aspect
public class HelloAspect {
    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)") // 포인트컷 자리
    public void validationCheck(JoinPoint jp) {
        Object[] args = jp.getArgs();

        for (Object arg : args) {
            if (arg instanceof Integer) {
                Integer integer = (Integer) arg;

                System.out.println(arg + "번아 안녕");
            }
        }
    }
}