package springmvc.servlet.web.frontcontroller.v3.controller;

import springmvc.servlet.web.frontcontroller.ModelView;
import springmvc.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    // HTTP 요청 처리 메소드 구현 - 회원 등록 폼 요청 처리
    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 모델과 뷰의 이름이 저장된 ModelView 객체 반환
        return new ModelView("new-form");
    }
}
