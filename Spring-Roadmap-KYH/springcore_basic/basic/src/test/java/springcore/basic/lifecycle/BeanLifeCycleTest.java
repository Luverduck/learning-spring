package springcore.basic.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    // 설정 클래스
    @Configuration
    static class lifeCycleConfig {
        @Bean
        public NetworkClient netWorkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

    @Test
    public void lifecycleTest() {
        // 스프링 컨테이너 생성
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(lifeCycleConfig.class);
        // 컨테이너에서 NetworkClient 타입 빈 반환
        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        // 스프링 컨테이너 종료
        ac.close();
    }
    
}
