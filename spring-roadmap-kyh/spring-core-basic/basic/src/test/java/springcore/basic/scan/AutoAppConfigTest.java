package springcore.basic.scan;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcore.basic.AutoAppConfig;
import springcore.basic.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    void beanScan() {
        // 스프링 컨테이너 생성
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        // 컨테이너에서 MemberService 타입으로 빈 조회하여 반환
        MemberService bean = ac.getBean(MemberService.class);
        // 반환한 빈 출력
        System.out.println("bean = " + bean);
        // 테스트 성공 조건은 반환된 빈의 타입이 MemberService 또는 그 하위 클래스인 경우이다.
        assertThat(bean).isInstanceOf(MemberService.class);
    }

}
