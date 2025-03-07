package springcore.basic.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springcore.basic.discount.DiscountPolicy;
import springcore.basic.discount.FixDiscountPolicy;
import springcore.basic.discount.RateDiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    // 둘 이상의 하위 타입을 갖는 상위 타입의 빈이 정의된 설정 클래스
    @Configuration
    static class ExtendsBeanConfig {
        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy(); // FixDiscountPolicy extends DiscountPolicy
        }
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy(); // RateDiscountPolicy extends DiscountPolicy
        }
    }

    // 스프링 컨테이너 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ExtendsBeanConfig.class);

    @Test
    @DisplayName("컨테이너에서 상위 타입으로 빈 조회 => NoUniqueBeanDefinitionException 예외 발생")
    void findBeanBySuperType() {
        // 컨테이너에서 DiscountPolicy 타입으로 빈을 조회한다.
        // 테스트 성공 조건은 빈 조회 중 NoUniqueBeanDefinitionException 예외의 발생이다.
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("컨테이너에서 상위 타입과 이름으로 빈 조회")
    void findBeanBySuperTypeWithName() {
        // 컨테이너에서 이름이 rateDiscountPolicy이며 타입이 DiscountPolicy인 빈을 조회한다.
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        // 테스트 성공 조건은 반환한 객체의 타입이 RateDiscountPolicy인 경우이다.
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("컨테이너에서 하위 타입으로 빈 조회")
    void findBeanBySubType() {
        // 컨테이너에서 RateDiscountPolicy 타입으로 빈을 조회한다.
        DiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        // 테스트 성공 조건은 반환된 객체의 타입이 RateDiscountPolicy인 경우이다.
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("컨테이너에서 상위 타입으로 모든 빈 조회")
    void findAllBeanBySuperType() {
        // 컨테이너에서 타입이 DiscountPolicy이거나 그 하위 타입인 모든 빈을 Map 형태로 반환한다.
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        // 반환한 Map에서 key와 value를 출력한다.
        for(String key : beansOfType.keySet())
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        // 반환한 Map을 출력한다.
        System.out.println("beansOfType = " + beansOfType);
        // 테스트 성공 조건은 반환한 Map에 저장된 요소의 수가 2인 경우이다.
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("컨테이너에서 Object 타입으로 모든 빈 조회")
    void findAllBeanByObjectType() {
        // 컨테이너에서 타입이 Object이거나 그 하위 타입인 모든 빈을 Map 형태로 반환한다.
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        // 반환한 Map에서 key와 value를 출력한다.
        for(String key : beansOfType.keySet())
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        // 반환한 Map을 출력한다.
        System.out.println("beansOfType = " + beansOfType);
    }

}
