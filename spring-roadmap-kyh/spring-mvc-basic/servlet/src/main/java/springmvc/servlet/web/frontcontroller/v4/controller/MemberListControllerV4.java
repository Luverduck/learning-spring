package springmvc.servlet.web.frontcontroller.v4.controller;

import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;
import springmvc.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // HTTP 요청 처리 메소드 구현 - 회원 조회 요청 처리
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 비즈니스 로직 실행
        List<Member> members = memberRepository.findAll();
        // 비즈니스 로직의 결과를 모델에 저장
        model.put("members", members);
        // 뷰의 이름 반환
        return "members";
    }
}
