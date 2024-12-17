package springcore.basic;

import springcore.basic.discount.FixDiscountPolicy;
import springcore.basic.member.MemberService;
import springcore.basic.member.MemberServiceImpl;
import springcore.basic.member.MemoryMemberRepository;
import springcore.basic.order.OrderService;
import springcore.basic.order.OrderServiceImpl;

public class AppConfig {

    // MemberService의 구현체 생성
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    // OrderService의 구현체 생성
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
