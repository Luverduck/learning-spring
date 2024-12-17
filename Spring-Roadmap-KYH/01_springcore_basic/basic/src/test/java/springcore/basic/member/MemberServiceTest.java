package springcore.basic.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springcore.basic.AppConfig;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void before() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // Given : 시나리오 진행에 필요한 값을 설정, 테스트의 상태를 설정  [준비]
        Member member = new Member(1L, "memberA", Grade.VIP);

        // When : 시나리오 진행 필요조건 명시, 테스트하고자 하는 행동  [실행]
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // Then : 시나리오를 완료했을 때 보장해야 하는 결과를 명시, 예상되는 변화 설명  [검증]
        Assertions.assertThat(member).isEqualTo(findMember);        // 성공
        //Assertions.assertThat(member).isNotEqualTo(findMember);   // 실패
    }

}
