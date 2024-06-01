package hello.hello_spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// [01-03] @Controller 어노테이션
@Controller
public class HelloController {

    // [01-03] START
    // 01. 웹 브라우저에서 http://localhost:8080/hello로 보낸 요청한다.
    // 02. /hello인 요청을 HelloController의 hello 메소드로 연결한다.
    // 03. hello 메소드에서 Model에 key-value 형태의 속성 {"data" : "Hello!!!"}을 추가한다.
    // 04. hello 메소드 실행 후 View 이름인 "hello"를 반환한다.
    // 05. ViewResolver는 templates 폴더 하위에서 이름이 "hello"인 템플릿을 찾는다.
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello!!!");
        return "hello";
    };
    // [01-03] END
}