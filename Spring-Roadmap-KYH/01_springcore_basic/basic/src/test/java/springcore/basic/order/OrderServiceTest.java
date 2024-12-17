package springcore.basic.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springcore.basic.AppConfig;
import springcore.basic.member.Grade;
import springcore.basic.member.Member;
import springcore.basic.member.MemberService;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void before() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // Given : 시나리오 진행에 필요한 값을 설정, 테스트의 상태를 설정  [준비]
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // When : 시나리오 진행 필요조건 명시, 테스트하고자 하는 행동  [실행]
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // Then : 시나리오를 완료했을 때 보장해야 하는 결과를 명시, 예상되는 변화 설명  [검증]
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
