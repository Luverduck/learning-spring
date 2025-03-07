package springcore.basic.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import springcore.basic.member.Member;

import java.util.Optional;

public class AutowiredTest {

    static class TestAppConfig {
        // 메소드에 @Autowired 또는 @Autowired(required = false)를 적용하는 경우
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }
        // 메소드에 @Autowired를 적용하고 매개변수에 @Nullable을 적용하는 경우
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }
        // 메소드에 @Autowired를 적용하고 매개변수 타입을 Optional<Member>로 정의한 경우
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }

    @Test
    void autowiredOption() {
        // 스프링 컨테이너 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestAppConfig.class);
    }

}
