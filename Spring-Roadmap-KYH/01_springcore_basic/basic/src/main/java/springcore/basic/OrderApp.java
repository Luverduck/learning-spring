package springcore.basic;

import springcore.basic.member.Grade;
import springcore.basic.member.Member;
import springcore.basic.member.MemberService;
import springcore.basic.member.MemberServiceImpl;
import springcore.basic.order.Order;
import springcore.basic.order.OrderService;
import springcore.basic.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        // 회원 서비스 생성
        MemberService memberService = new MemberServiceImpl();
        // 주문 서비스 생성
        OrderService orderService = new OrderServiceImpl();

        // 회원 생성 및 회원 가입
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문 생성 및 생성된 주문 반환
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // 생성된 주문 확인
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
