package springmvc.servlet.web.frontcontroller.v4.controller;

import springmvc.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    // HTTP 요청 처리 메소드 구현 - 회원 등록 폼 요청 처리
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 뷰의 이름 반환
        return "new-form";
    }
}
