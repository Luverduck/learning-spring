package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// [05-01] START
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // /src/main/resources/templates 하위에서 'home'이라는 이름의 뷰를 찾는다.
        return "home";
    }
}
// [05-01] END