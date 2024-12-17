package springcore.basic;

import springcore.basic.member.Grade;
import springcore.basic.member.Member;
import springcore.basic.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        // AppConfig 생성
        AppConfig appConfig = new AppConfig();

        // 회원 서비스 생성
        MemberService memberService = appConfig.memberService();

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
