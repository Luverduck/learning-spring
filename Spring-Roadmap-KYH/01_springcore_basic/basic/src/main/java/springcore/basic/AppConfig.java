package springcore.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springcore.basic.discount.DiscountPolicy;
import springcore.basic.discount.RateDiscountPolicy;
import springcore.basic.member.MemberRepository;
import springcore.basic.member.MemberService;
import springcore.basic.member.MemberServiceImpl;
import springcore.basic.member.MemoryMemberRepository;
import springcore.basic.order.OrderService;
import springcore.basic.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    // MemberService
    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    // OrderService
    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // MemberRepository
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    // DiscountPolicy
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
