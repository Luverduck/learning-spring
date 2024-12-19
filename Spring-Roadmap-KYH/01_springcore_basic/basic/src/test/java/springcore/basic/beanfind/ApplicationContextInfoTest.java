package springcore.basic.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcore.basic.AppConfig;

public class ApplicationContextInfoTest {

    // 스프링 컨테이너 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("컨테이너에 등록된 모든 빈 출력하기")
    void findAllBean() {
        // 스프링 컨테이너에 등록된 모든 빈의 이름 조회
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // 빈의 이름과 빈 객체 출력
        for(String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("컨테이너에 등록된 애플리케이션 빈 출력하기")
    void findApplicationBean() {
        // 스프링 컨테이너에 등록된 모든 빈의 이름 조회
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // 빈의 이름과 빈 객체 출력
        for(String beanDefinitionName : beanDefinitionNames) {
            // 빈 이름으로 빈 정의 반환
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            // 빈 정의에서 역할이 ROLE_APPLICATION 일 경우에만 출력
            // - ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // - ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }

}
