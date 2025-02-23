package springmvc.servlet.web.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.web.frontcontroller.MyView;
import springmvc.servlet.web.frontcontroller.v2.ControllerV2;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    // HTTP 요청 처리 메소드 구현 - 회원 등록 폼 요청 처리
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 회원 등록 폼 HTML 문서를 생성하는 뷰 반환
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
