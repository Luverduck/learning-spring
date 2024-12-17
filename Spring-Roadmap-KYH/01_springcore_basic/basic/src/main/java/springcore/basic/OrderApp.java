package springcore.basic;

import springcore.basic.member.Grade;
import springcore.basic.member.Member;
import springcore.basic.member.MemberService;
import springcore.basic.order.Order;
import springcore.basic.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        // AppConfig 생성
        AppConfig appConfig = new AppConfig();

        // 회원 서비스 생성
        MemberService memberService = appConfig.memberService();
        // 주문 서비스 생성
        OrderService orderService = appConfig.orderService();

        // 회원 생성 및 회원 가입
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문 생성 및 생성된 주문 반환
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        // 생성된 주문 확인
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
