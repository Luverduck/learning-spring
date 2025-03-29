package springmvc.mvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    // 뷰 템플릿 사용 - 뷰의 이름을 ModelAndView 객체로 반환
    // - 뷰의 이름을 ModelAndView 객체로 반환할 수 있다.
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        // 뷰의 이름을 지정하여 ModelAndView 객체 생성
        ModelAndView mv = new ModelAndView("response/hello");
        // ModelAndView 객체에 데이터 저장
        mv.addObject("data", "hello!");
        // ModelAndView 객체 반환
        return mv;
    }

    // 뷰 템플릿 사용 - 뷰의 이름을 문자열로 반환
    // - 뷰의 이름을 문자열로 직접 반환할 수 있다.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        // Model 객체에 데이터 저장
        model.addAttribute("data", "hello!");
        // 뷰의 이름 반환
        return "response/hello";
    }

    // 뷰 템플릿 사용 - 요청 URL을 뷰의 이름으로 사용
    // - 핸들러 메소드의 반환형이 void일 경우 요청 URL을 뷰의 이름으로 사용한다.
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        // Model 객체에 데이터 저장
        model.addAttribute("data", "hello!");
    }
}
