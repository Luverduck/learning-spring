package springcore.basic.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcore.basic.AppConfig;
import springcore.basic.member.MemberService;
import springcore.basic.member.MemberServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicBeanFindTest {

    // 스프링 컨테이너 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("컨테이너에서 이름으로 빈 조회")
    void findBeanByName() {
        // 컨테이너에서 이름이 memberService인 빈을 조회하여 반환한다.
        Object bean = ac.getBean("memberService");
        // 테스트 성공 조건은 반환한 객체가 MemberServiceImpl 타입인 경우이다.
        Assertions.assertThat(bean.getClass().getName()).isEqualTo(MemberServiceImpl.class.getName());
    }

    @Test
    @DisplayName("컨테이너에서 이름과 타입으로 빈 조회")
    void findBeanByNameWithType() {
        // 컨테이너에서 이름이 memberService이며 타입이 MemberService인 빈을 조회하여 반환한다.
        MemberService bean = ac.getBean("memberService", MemberService.class);
        // 테스트 성공 조건은 반환한 객체가 MemberServiceImpl 타입인 경우이다.
        Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("컨테이너에서 타입으로 빈 조회")
    void findBeanByType() {
        // 컨테이너에서 타입이 MemberRepository인 빈을 조회하여 반환한다.
        MemberService bean = ac.getBean(MemberService.class);
        // 테스트 성공 조건은 반환한 객체가 MemberServiceImpl 타입인 경우이다.
        Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("컨테이너에서 구현 타입으로 빈 조회")
    void findBeanByImplType() {
        // 컨테이너에서 타입이 MemberServiceImpl인 빈을 조회하여 반환한다.
        MemberService bean = ac.getBean(MemberServiceImpl.class);
        // 테스트 성공 조건은 반환한 객체가 MemberServiceImpl 타입인 경우이다.
        Assertions.assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("컨테이너에서 없는 이름으로 빈을 조회하는 경우")
    void findBeanByNameX() {
        // 컨테이너에서 이름이 xxxx이며 타입이 MemberService인 빈을 조회하여 반환한다.
        // 테스트 성공 조건은 빈 조회 중 NoSuchBeanDefinitionException 예외의 발생이다.
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
    }

}
