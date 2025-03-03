package springmvc.servlet.web.frontcontroller.v4.controller;

import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;
import springmvc.servlet.web.frontcontroller.ModelView;
import springmvc.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // HTTP 요청 처리 메소드 구현 - 회원 등록 요청 처리
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 비즈니스 로직 처리
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);
        // 비즈니스 로직의 결과를 모델에 저장
        model.put("member", member);
        // 뷰의 이름 반환
        return "save-result";
    }
}
