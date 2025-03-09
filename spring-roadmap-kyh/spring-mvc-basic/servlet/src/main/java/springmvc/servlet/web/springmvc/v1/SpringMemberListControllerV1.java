package springmvc.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;

import java.util.List;

@Controller
public class SpringMemberListControllerV1 {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // HTTP 요청 처리 메소드 구현 - 회원 조회 요청 처리
    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {
        // 비즈니스 로직 실행
        List<Member> members = memberRepository.findAll();
        // 비즈니스 로직의 결과를 모델에 저장
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        // 모델과 뷰의 이름이 저장된 ModelAndView 객체 반환
        return mv;
    }
}
