package springmvc.servlet.web.springmvc.v3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members/")
public class SpringMemberControllerV3 {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    // 회원 등록 폼 요청 처리
    @GetMapping("/new-form")
    public String newForm() {
        // 뷰의 이름 반환
        return "new-form";
    }

    // 회원 등록 요청 처리
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {
        // 비즈니스 로직 처리
        Member member = new Member(username, age);
        memberRepository.save(member);
        // 비즈니스 로직의 결과를 모델에 저장
        model.addAttribute("member", member);
        // 뷰의 이름 반환
        return "save-result";
    }

    // 회원 조회 요청 처리
    @GetMapping()
    public String members(Model model) {
        // 비즈니스 로직 실행
        List<Member> members = memberRepository.findAll();
        // 비즈니스 로직의 결과를 모델에 저장
        model.addAttribute("members", members);
        // 뷰의 이름 반환
        return "members";
    }
}
