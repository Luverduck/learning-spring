package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// [05-01] START
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
// [05-01] END