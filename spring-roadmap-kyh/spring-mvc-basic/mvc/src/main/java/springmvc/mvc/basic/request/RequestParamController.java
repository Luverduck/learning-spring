package springmvc.mvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // HTTP 요청 파라미터 - 쿼리 스트링, HTML 폼
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // HTTP 요청에 포함된 요청 파라미터 반환
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        // HTTP 응답 본문에 응답 반환
        response.getWriter().write("ok");
    }

    // HTTP 요청 파라미터 - @RequestParam
    // - 지정한 이름의 요청 파라미터의 값을 핸들러 메소드의 매개변수에 바인딩한다.
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        // HTTP 요청에 포함된 요청 파라미터 반환
        log.info("username={}, age={}", memberName, memberAge);
        // HTTP 응답 본문에 응답 반환
        return "ok";
    }

    // HTTP 요청 파라미터 - @RequestParam
    // - 핸들러 메소드의 매개변수 이름이 요청 파라미터의 이름과 같은 경우
    //   @RequestParam에서 요청 파라미터의 이름 지정을 생략할 수 있다.
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        // HTTP 요청에 포함된 요청 파라미터 반환
        log.info("username={}, age={}", username, age);
        // HTTP 응답 본문에 응답 반환
        return "ok";
    }

    // HTTP 요청 파라미터 - @RequestParam
    // - 핸들러 메소드의 매개변수 이름이 요청 파라미터의 이름과 같은 경우
    //   기본 타입, Wrapper 타입 및 String 타입에 한하여 @RequestParam을 생략할 수 있다.
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age
    ) {
        // HTTP 요청에 포함된 요청 파라미터 반환
        log.info("username={}, age={}", username, age);
        // HTTP 응답 본문에 응답 반환
        return "ok";
    }

    // HTTP 요청 파라미터 - @RequestParam
    // - @RequestParam의 required를 통해 요청 파라미터의 필수 여부를 설정할 수 있다.
    //   required = true : 요청 매핑을 위해 지정한 이름의 요청 파라미터를 반드시 포함해야 한다.
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) int age
    ) {
        // HTTP 요청에 포함된 요청 파라미터 반환
        log.info("username={}, age={}", username, age);
        // HTTP 응답 본문에 응답 반환
        return "ok";
    }

    // HTTP 요청 파라미터 - @RequestParam
    // - @RequestParam의 defaultValue를 통해 요청 파라미터의 기본값을 설정할 수 있다.
    //   defaultValue가 지정되면 요청 파라미터가 포함되지 않아도 기본값으로 바인딩된다.
    //   defaultValue가 지정되면 required와 상관없이 핸들러 메소드의 매개변수에 값이 바인딩된다.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) {
        // HTTP 요청에 포함된 요청 파라미터 반환
        log.info("username={}, age={}", username, age);
        // HTTP 응답 본문에 응답 반환
        return "ok";
    }

    // HTTP 요청 파라미터 - @RequestParam
    // - @RequestParam를 Map<String, String> 또는 MultiValueMap<String, String> 타입에 적용할 경우
    //   모든 요청 파라미터를 Key-Value 형태로 바인딩한다.
    //   - Map<String, String> : 하나의 요청 파라미터의 이름에 하나의 값만 바인딩된다.
    //   - MultiValueMap<String, String> : 하나의 요청 파라미터의 이름에 여러 개의 값이 바인딩된다.
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap
    ) {
        // HTTP 요청에 포함된 요청 파라미터 반환
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        // HTTP 응답 본문에 응답 반환
        return "ok";
    }
}
