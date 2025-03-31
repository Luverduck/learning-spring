package springmvc.mvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import springmvc.mvc.basic.HelloData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    // ObjectMapper 생성 (JSON 문자열 <-> Java 객체 변환)
    private ObjectMapper objectMapper = new ObjectMapper();

    // 서블릿의 입력 스트림 사용
    // 서블릿에서 입력 스트림을 반환하여 HTTP 요청 바디의 JSON 데이터를 문자열로 읽을 수 있다.
    // - JSON 문자열은 ObjectMapper를 사용하여 Java 객체로 변환할 수 있다.
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 서블릿에서 입력 스트림 반환
        ServletInputStream inputStream = request.getInputStream();
        // 서블릿에서 반환된 입력 스트림을 통해 HTTP 요청 바디의 JSON 데이터를 문자열로 읽기
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        // ObjectMapper를 통해 JSON 문자열을 Java 객체로 변환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        response.getWriter().write("ok");
    }

    // @RequestBody 사용 - JSON 문자열로 바인딩 후 Java 객체로 변환
    // 핸들러의 매개변수에 @RequestBody를 적용하면 HTTP 요청 바디의 데이터가 바인딩된다.
    // 핸들러의 매개변수 타입이 문자열인 경우 JSON 형태의 문자열로 바인딩된다.
    // 바인딩된 문자열은 ObjectMapper를 통해 Java 객체로 변환할 수 있다.
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        // ObjectMapper를 통해 JSON 문자열을 Java 객체로 변환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    // @RequestBody 사용 - 핸들러의 매개변수에 직접 바인딩
    // 핸들러의 매개변수 타입이 JSON 변환 결과 타입인 경우 해당 타입으로 직접 바인딩된다.
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    // HttpEntity<T> 사용
    // HttpEntity<T>를 통해 HTTP 요청 바디의 데이터를 읽을 수 있다.
    // - HttpEntity<T>는 HTTP 메시지 전체를 추상화한 클래스로, HTTP 헤더와 바디의 데이터를 포함한다.
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> data) {
        // HTTP 요청 바디
        HelloData helloData = data.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    // @ResponseBody와 Java 객체의 반환
    // @ResponseBody가 적용된 핸들러는 반환 데이터를 HTTP 응답 바디에 포함시킨다.
    // 핸들러가 Java 객체를 반환할 경우 해당 타입의 필드값을 JSON 형태의 문자열로 변환하여 HTTP 응답 바디에 포함시킨다.
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        // @ResponseBody가 적용된 핸들러에서 Java 객체를 반환하면 JSON 형태의 문자열로 HTTP 응답 바디에 포함된다.
        return helloData;
    }
}
