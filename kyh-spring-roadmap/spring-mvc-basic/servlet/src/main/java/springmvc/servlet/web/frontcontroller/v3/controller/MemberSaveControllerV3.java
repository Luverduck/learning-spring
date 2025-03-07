package springmvc.servlet.web.frontcontroller.v3.controller;

import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;
import springmvc.servlet.web.frontcontroller.ModelView;
import springmvc.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // HTTP 요청 처리 메소드 구현 - 회원 등록 요청 처리
    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 비즈니스 로직 처리
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);
        // 비즈니스 로직의 결과를 모델에 저장
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        // 모델과 뷰의 이름이 저장된 ModelView 객체 반환
        return mv;
    }
}
