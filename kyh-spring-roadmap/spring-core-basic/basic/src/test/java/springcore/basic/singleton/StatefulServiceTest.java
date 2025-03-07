package springcore.basic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    // 설정 클래스(테스트용)
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }

    @Test
    void statefulServiceSingleton() {
        
        // 스프링 컨테이너 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        // 두 사용자 A, B의 주문 요청을 처리하기 위한 StatefulService 객체
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread-A가 A 사용자의 주문 요청 처리
        statefulService1.order("Thread-A", 10000);
        // Thread-B가 B 사용자의 주문 요청 처리
        statefulService2.order("Thread-B", 20000);

        // Thread-A가 처리한 주문의 가격 반환
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        // 테스트 성공 조건은 StatefulService의 가격 필드의 값이 10000이 아닌 경우이다.
        Assertions.assertThat(statefulService1.getPrice()).isNotEqualTo(10000);

    }

    @Test
    void statelessServiceSingleton() {

        // 스프링 컨테이너 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        // 두 사용자 A, B의 주문 요청을 처리하기 위한 StatefulService 객체
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        // Thread-A가 A 사용자의 주문 요청 처리
        int priceUserA = statelessService1.order("Thread-A", 10000);
        // Thread-B가 B 사용자의 주문 요청 처리
        int priceUserB = statelessService2.order("Thread-B", 20000);

        // Thread-A가 처리한 주문의 가격 반환
        System.out.println("price = " + priceUserA);

    }

}