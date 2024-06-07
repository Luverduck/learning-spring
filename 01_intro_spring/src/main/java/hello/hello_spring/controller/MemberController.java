package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// [04-01] START
@Controller
public class MemberController {

    // 회원 서비스 선언
    private final MemberService memberService;

    // 회원 서비스의 생성자 정의
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // MemberController와 MemberService 사이의 의존관계 생성
    // 1. MemberController형 객체를 생성하기 위해서는 생성자를 호출해야 한다.
    // 2. 스프링 컨테이너에서 전달받은 MemberService형 객체를 인수로 생성자를 호출한다.
    // 3. MemberController(this)형 객체의 멤버인 memberService에 스프링 컨테이너에서 전달받은 MemberService형 객체를 대입한다.

    // [05-02] START
    /**
     * 회원 등록 화면으로 이동
     * @return
     */
    @GetMapping("/members/new")
    public String createForm() {
        // /src/main/resources/templates 하위에서 'members' 하위의 'createMemberForm'이라는 이름의 뷰를 찾는다.
        return "members/createMemberForm";
    }

    /**
     * 회원 등록 화면에서 보낸 데이터를 받아 회원을 등록
     * @param form
     * @return
     */
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        // 멤버 객체를 생성한 후 필드를 뷰에서 전달받은 값으로 설정한다.
        Member member = new Member();
        member.setName(form.getName());
        // 회원을 등록한다.
        memberService.join(member);
        // 홈 화면으로 강제 이동 시킨다.
        return "redirect:/";
    }
    // [05-02] END
}
// [04-01] END
