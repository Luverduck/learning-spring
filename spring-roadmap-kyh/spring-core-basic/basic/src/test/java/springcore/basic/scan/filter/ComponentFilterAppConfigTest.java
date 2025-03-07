package springcore.basic.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;


public class ComponentFilterAppConfigTest {

    // 필터가 적용된 컴포넌트 스캔을 사용하는 설정 클래스
    @Configuration
    @ComponentScan(
        // @Filter의 type은 기본값이 ANNOTATION이며, 생략할 수 있다
        includeFilters = @ComponentScan.Filter(classes = MyIncludeComponent.class),
        excludeFilters = @ComponentScan.Filter(classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }

    // 컴포넌트 스캔의 필터 적용 테스트
    @Test
    void filterScan() {
        // 스프링 컨테이너 생성
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        
        // 컨테이너에서 이름이 beanA이며, 타입이 BeanA인 빈을 조회하여 반환
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        // 컨테이너에서 이름이 beanB이며, 타입이 BeanB인 빈을 조회하여 반환
        // excludeFilters에 따라 MyExcludeComponent 타입 어노테이션이 붙어있는 경우 컴포넌트 스캔의 대상에서 제외
        // BeanB은 @MyExcludeComponent이 붙어있으므로 컴포넌트 스캔 대상에서 제외 => NoSuchBeanDefinitionException 예외 발생
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }

}
