package springmvc.servlet.web.frontcontroller.v3.controller;

import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;
import springmvc.servlet.web.frontcontroller.ModelView;
import springmvc.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // HTTP 요청 처리 메소드 구현 - 회원 조회 요청 처리
    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 비즈니스 로직 실행
        List<Member> members = memberRepository.findAll();
        // 비즈니스 로직의 결과를 모델에 저장
        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);
        // 모델과 뷰의 이름이 저장된 ModelView 객체 반환
        return mv;
    }
}
