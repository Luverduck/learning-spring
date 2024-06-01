package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// [01-03] @Controller 어노테이션
@Controller
public class HelloController {

    // [01-03] START
    // 01. 웹 브라우저에서 'http://localhost:8080/hello'로 요청을 보낸다.
    // 02. '/hello'인 요청을 HelloController의 hello 메소드로 연결한다.
    // 03. hello 메소드에서 Model에 key-value 형태의 속성 {"data" : "Hello!!!"}을 추가한다.
    // 04. hello 메소드 실행 후 View 이름인 "hello"를 반환한다.
    // 05. ViewResolver는 templates 폴더 하위에서 이름이 "hello"인 템플릿을 찾는다.
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello!!!");
        return "hello";
    };
    // [01-03] END

    // [02-02] START
    // 01. 웹 브라우저에서 'http://localhost:8080/hello-mvc'로 요청을 보낸다.
    // 02. '/hello-mvc'인 요청을 HelloController의 helloMVC 메소드로 연결한다.
    //     key가 name인 파라미터의 값을 helloMVC 메소드의 매개변수 name에 전달한다.
    // 03. helloMVC 메소드에서 Model에 key-value 형태의 속성 {"data" : "Hello!!!"}을 추가한다.
    // 04. helloMVC 메소드 실행 후 View 이름인 "hello-template"를 반환한다.
    // 05. ViewResolver는 templates 폴더 하위에서 이름이 "hello-template"인 템플릿을 찾는다.
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    };
    // [02-02] END

    // [02-03] START
    // 01. 웹 브라우저에서 'http://localhost:8080/hello-string'로 요청을 보낸다.
    // 02. '/hello-string'인 요청을 HelloController의 helloString 메소드로 연결한다.
    //     key가 name인 파라미터의 값을 helloMVC 메소드의 매개변수 name에 전달한다.
    // 03. helloMVC 메소드 실행 후 반환한 값을 문자열로 변환하여 HTTP message body에 반환한다.
    //     반환한 값이 문자열일 경우 StringHttpMessageConverter가 동작하여 문자열로 변환한다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "Hello " + name;
    };
    //     객체일 경우 MappingJackson2HttpMessageConverter가 동작하여 JSON 문자열로 변환한다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloAPI(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    // 04. 웹 브라우저에서 변환한 문자열을 표시한다.

    static public class Hello {
        public String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    // [02-03] END
}
