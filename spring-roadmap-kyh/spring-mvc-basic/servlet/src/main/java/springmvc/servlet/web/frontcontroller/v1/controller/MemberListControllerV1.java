package springmvc.servlet.web.frontcontroller.v1.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;
import springmvc.servlet.web.frontcontroller.v1.ControllerV1;

import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements ControllerV1 {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // HTTP 요청 처리 메소드 구현 - 회원 조회 요청 처리
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 회원 저장소에 등록된 모든 회원 조회
        List<Member> members = memberRepository.findAll();
        // 반환한 회원 정보를 Attribute에 저장
        request.setAttribute("members", members);
        // 응답으로 반환할 뷰의 경로
        String viewPath = "/WEB-INF/views/members.jsp";
        // 클라이언트의 요청을 지정한 뷰로 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
