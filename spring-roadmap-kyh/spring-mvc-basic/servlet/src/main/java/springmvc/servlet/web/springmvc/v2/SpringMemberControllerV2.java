package springmvc.servlet.web.springmvc.v2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members/")
public class SpringMemberControllerV2 {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // 회원 등록 폼 요청 처리
    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        // 모델과 뷰의 이름이 저장된 ModelAndView 객체 반환
        return new ModelAndView("new-form");
    }

    // 회원 등록 요청 처리
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
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

    // 회원 조회 요청 처리
    @RequestMapping()
    public ModelAndView members() {
        // 비즈니스 로직 실행
        List<Member> members = memberRepository.findAll();
        // 비즈니스 로직의 결과를 모델에 저장
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        // 모델과 뷰의 이름이 저장된 ModelAndView 객체 반환
        return mv;
    }
}
