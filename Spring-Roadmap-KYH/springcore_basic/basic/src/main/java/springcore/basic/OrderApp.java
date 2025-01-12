package springcore.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcore.basic.member.Grade;
import springcore.basic.member.Member;
import springcore.basic.member.MemberService;
import springcore.basic.order.Order;
import springcore.basic.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {

        // 스프링 컨테이너 생성
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        
        // 회원 서비스 의존 관계 주입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        // 주문 서비스 의존 관계 주입
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

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
