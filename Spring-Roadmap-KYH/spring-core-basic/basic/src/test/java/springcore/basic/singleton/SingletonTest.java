package springcore.basic.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcore.basic.AppConfig;
import springcore.basic.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        // DI 컨테이너 생성
        AppConfig appConfig = new AppConfig();

        // 클라이언트의 요청을 처리하기 위해 컨테이너에서 MemberService 반환
        MemberService memberService1 = appConfig.memberService();
        // 클라이언트의 요청을 처리하기 위해 컨테이너에서 MemberService 반환
        MemberService memberService2 = appConfig.memberService();

        // 두 객체의 참조값이 다른 것을 확인 => 매 번 요청을 처리하기 위해 MemberService 객체를 생성한다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService1 = " + memberService2);

        // 테스트 성공 조건은 두 객체의 참조가 다른 경우이다.
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체의 사용")
    void singletonServiceTest() {
        // 싱글톤 패턴이 적용된 객체의 반환
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        
        // 두 객체의 참조값 비교
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // 테스트 성공 조건은 두 객체의 참조가 같은 경우이다.
        assertThat(singletonService1).isSameAs(singletonService2);
        // [참고]
        // isSameAs() : 두 객체의 참조가 같은지 비교 ( == )
        // isEqualTo() : 두 객체의 값이 같은지 비교 ( equals() )
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // 스프링 컨테이너 생성
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 클라이언트의 요청을 처리하기 위해 컨테이너에서 MemberService 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        // 클라이언트의 요청을 처리하기 위해 컨테이너에서 MemberService 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 두 객체의 참조값이 같은 것을 확인 => 매 번 요청을 처리하기 위해 기존에 생성했던 MemberService 객체를 반환한다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService1 = " + memberService2);

        // 테스트 성공 조건은 두 객체의 참조가 같은 경우이다.
        assertThat(memberService1).isSameAs(memberService2);
    }

}
