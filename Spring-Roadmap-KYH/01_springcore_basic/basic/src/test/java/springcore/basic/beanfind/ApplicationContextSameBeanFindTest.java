package springcore.basic.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springcore.basic.member.MemberRepository;
import springcore.basic.member.MemoryMemberRepository;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    // 동일한 타입의 빈 정의를 포함하는 설정 클래스
    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    // 스프링 컨테이너 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("동일한 타입의 빈이 둘 이상 존재하는 컨테이너에서 이름으로 빈 조회")
    void findBeanByName() {
        // 컨테이너에서 이름이 memberRepository1인 빈을 조회하여 반환한다.
        MemberRepository bean = ac.getBean("memberRepository1", MemberRepository.class);
        // 테스트 성공 조건은 반환한 객체가 MemberRepository 타입인 경우이다.
        assertThat(bean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("동일한 타입의 빈이 둘 이상 존재하는 컨테이너에서 타입으로 빈을 조회하면 예외가 발생한다.")
    void findBeanByTypeDuplicate() {
        // 컨테이너에서 타입이 MemberRepository인 빈을 조회한다.
        // 테스트 성공 조건은 빈 조회 중 NoUniqueBeanDefinitionException 예외의 발생이다.
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemoryMemberRepository.class));
    }

    @Test
    @DisplayName("동일한 타입의 빈이 둘 이상 존재하는 컨테이너에서 특정 타입으로 등록된 모든 빈 조회")
    void findAllBeanByName() {
        // 컨테이너에서 타입이 MemberRepository인 모든 빈을 조회하여 Map 형태로 반환한다.
        Map<String, MemberRepository> beans = ac.getBeansOfType(MemberRepository.class);
        // 반환한 Map에서 key와 value를 출력한다.
        for(String key : beans.keySet())
            System.out.println("key = " + key + " value = " + beans.get(key));
        // 반환한 Map을 출력한다.
        System.out.println("beans = " + beans);
        // 테스트 성공 조건은 반환한 Map에 저장된 요소의 수가 2인 경우이다.
        assertThat(beans.size()).isEqualTo(2);
    }

}
