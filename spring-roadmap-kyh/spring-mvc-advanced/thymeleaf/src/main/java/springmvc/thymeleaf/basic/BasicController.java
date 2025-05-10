package springmvc.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    // th:text와 [[...]]
    @GetMapping("text-basic")
    public String textBasic(Model model) {
        // Model에 데이터 추가
        model.addAttribute("data", "Hello, Spring!");
        return "basic/text-basic";
    }

    // 이스케이프 vs 언이스케이프
    @GetMapping("text-unescaped")
    public String textUnescaped(Model model) {
        // Model에 사용이 예약된 특수문자가 포함된 데이터 추가
        model.addAttribute("data", "Hello, <b>Spring!</b>");
        return "basic/text-unescaped";
    }
}