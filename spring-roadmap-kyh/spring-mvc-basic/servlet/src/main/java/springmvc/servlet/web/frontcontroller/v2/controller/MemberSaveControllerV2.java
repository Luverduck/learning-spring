package springmvc.servlet.web.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;
import springmvc.servlet.web.frontcontroller.MyView;
import springmvc.servlet.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // HTTP 요청 처리 메소드 구현 - 회원 등록 요청 처리
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청으로부터 요청 파라미터 반환
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        // 회원 저장소에 회원 정보 저장
        Member member = new Member(username, age);
        memberRepository.save(member);
        // 회원 저장소에 저장된 회원 정보를 Attribute에 저장
        request.setAttribute("member", member);
        // 회원 저장 HTML 문서를 생성하는 뷰 반환
        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
