package springmvc.thymeleaf.basic;

import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    // th:text와 [[...]]
    @GetMapping("text-basic")
    public String textBasic(Model model) {
        // Model에 데이터 추가
        model.addAttribute("data", "Hello, Spring!");
        // 뷰의 이름 반환
        return "basic/text-basic";
    }

    // 이스케이프 vs 언이스케이프
    @GetMapping("text-unescaped")
    public String textUnescaped(Model model) {
        // Model에 사용이 예약된 특수문자가 포함된 데이터 추가
        model.addAttribute("data", "Hello, <b>Spring!</b>");
        // 뷰의 이름 반환
        return "basic/text-unescaped";
    }

    // 변수 표현식
    @GetMapping("/variable")
    public String variable(Model model) {
        // 반환할 데이터 생성
        User userA = new User("userA", 10);
        User userB = new User("userA", 20);
        // 반환할 데이터를 List 형태로 생성
        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);
        // 반환할 데이터를 Map 형태로 생성
        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);
        // Model에 단일 객체, List, Map 형태의 데이터 추가
        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);
        // 뷰의 이름 반환
        return "basic/variable";
    }

    // 스프링 빈
    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello, " + data;
        }
    }

    @Data
    static class User {
        private String username;
        private int age;
        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    // 기본 객체
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello, Session!");
        return "basic/basic-objects";
    }
}