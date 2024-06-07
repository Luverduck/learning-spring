package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
// [04-01] END
