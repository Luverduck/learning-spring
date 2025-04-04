package springmvc.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberFormControllerV1 {

    // HTTP 요청 처리 메소드 구현 - 회원 등록 폼 요청 처리
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        // 모델과 뷰의 이름이 저장된 ModelAndView 객체 반환
        return new ModelAndView("new-form");
    }
}
