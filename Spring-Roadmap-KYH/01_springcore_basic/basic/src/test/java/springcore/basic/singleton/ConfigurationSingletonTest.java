package springcore.basic.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcore.basic.AppConfig;
import springcore.basic.member.MemberRepository;
import springcore.basic.member.MemberServiceImpl;
import springcore.basic.order.OrderServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        // 스프링 컨테이너 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 컨테이너에서 MemberRepository 타입 빈 반환
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 컨테이너에서 MemberServiceImpl 타입 빈 반환
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // 컨테이너에서 MemberServiceImpl 타입 빈 반환
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        // MemberServiceImpl 타입 빈 안에 정의된 필드 memberRepository의 값 반환
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        // OrderServiceImpl 타입 빈 안에 정의된 필드 memberRepository의 값 반환
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 컨테이너에서 반환한 MemberRepository 타입 빈과
        // 의존 관계가 주입된 빈에서 반환한 MemberRepository 타입 빈의 참조 비교
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);

        // 테스트 성공 조건은 반환한 모든 빈의 참조가 같은 경우이다.
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

}
