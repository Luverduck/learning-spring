package springcore.basic.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import springcore.basic.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContextTest {

    @Test
    void xmlAppContext() {
        // appConfig.xml을 설정 정보로 하여 스프링 컨테이너 생성
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        // 컨테이너에서 이름이 memberService이며, 타입이 MemberService인 빈을 조회한다.
        MemberService bean = ac.getBean("memberService", MemberService.class);
        // 테스트 성공 조건은 반환한 객체의 타입이 MemberService인 경우이다.
        assertThat(bean).isInstanceOf(MemberService.class);
    }

}
