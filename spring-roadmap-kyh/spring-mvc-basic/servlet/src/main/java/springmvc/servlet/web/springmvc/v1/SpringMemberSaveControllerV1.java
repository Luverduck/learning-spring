package springmvc.servlet.web.springmvc.v1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;

@Controller
public class SpringMemberSaveControllerV1 {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // HTTP 요청 처리 메소드 구현 - 회원 등록 요청 처리
    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        // 비즈니스 로직 처리
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);
        // 비즈니스 로직의 결과를 모델에 저장
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        // 모델과 뷰의 이름이 저장된 ModelAndView 객체 반환
        return mv;
    }
}
