package springcore.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcore.basic.member.Grade;
import springcore.basic.member.Member;
import springcore.basic.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {

        // 스프링 컨테이너 생성
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 회원 서비스 의존 관계 주입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 회원 생성
        Member member = new Member(1L, "memberA", Grade.VIP);
        // 회원 가입
        memberService.join(member);
        // 회원 조회
        Member findMember = memberService.findMember(1L);

        // 검증
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());

    }
}
