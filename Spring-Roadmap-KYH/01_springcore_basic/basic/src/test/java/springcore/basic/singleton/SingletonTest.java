package springcore.basic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springcore.basic.AppConfig;
import springcore.basic.member.MemberService;

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

        // 테스트 성공 조건은 두 객체의 참조가 다를 경우이다.
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

}
