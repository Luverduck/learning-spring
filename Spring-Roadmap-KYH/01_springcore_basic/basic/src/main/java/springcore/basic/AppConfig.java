package springcore.basic;

import springcore.basic.discount.DiscountPolicy;
import springcore.basic.discount.FixDiscountPolicy;
import springcore.basic.member.MemberRepository;
import springcore.basic.member.MemberService;
import springcore.basic.member.MemberServiceImpl;
import springcore.basic.member.MemoryMemberRepository;
import springcore.basic.order.OrderService;
import springcore.basic.order.OrderServiceImpl;

public class AppConfig {

    // MemberService
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    // OrderService
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // MemberRepository
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // DiscountPolicy
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}
