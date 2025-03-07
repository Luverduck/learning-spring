package springmvc.servlet.web.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;
import springmvc.servlet.web.frontcontroller.MyView;
import springmvc.servlet.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {
    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // HTTP 요청 처리 메소드 구현 - 회원 조회 요청 처리
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 회원 저장소에 등록된 모든 회원 조회
        List<Member> members = memberRepository.findAll();
        // 반환한 회원 정보를 Attribute에 저장
        request.setAttribute("members", members);
        // 회원 목록 JSP를 실행하는 뷰 처리 객체 반환
        return new MyView("/WEB-INF/views/members.jsp");
    }
}
