package springmvc.mvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    // HTTP 요청 메시지 - 단순 텍스트
    // - 서블릿의 입력 스트림를 통해 HTTP 요청 바디의 데이터를 읽을 수 있다.
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 서블릿에서 입력 스트림 반환
        ServletInputStream inputStream = request.getInputStream();
        // 서블릿에서 반환된 입력 스트림을 통해 HTTP 요청 바디의 데이터 읽기
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    // HTTP 요청 메시지 - 단순 텍스트
    // - 서블릿의 입출력 스트림은 핸들러 메소드의 입출력 스트림 타입의 매개변수에 바인딩된다.
    //   - 입력 스트림 : InputStream, Reader
    //   - 출력 스트림 : OutputStream, Writer
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        // 서블릿에서 반환된 입력 스트림을 통해 HTTP 요청 바디의 데이터 읽기
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    // HTTP 요청 메시지 - 단순 텍스트
    // - HttpEntity<T>를 통해 HTTP 요청 바디의 데이터를 읽을 수 있다.
    //   - HttpEntity<T>는 HTTP 메시지 전체를 추상화한 클래스로, HTTP 헤더와 바디의 데이터를 포함한다.
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        // HTTP 요청 헤더
        HttpHeaders headers = httpEntity.getHeaders();
        log.info("headers={}", headers);
        // HTTP 요청 바디
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        // HTTP 응답에 사용할 수 있다.
        return new HttpEntity<>("ok");
    }

    // HTTP 요청 메시지 - 단순 텍스트
    // - 핸들러 메소드의 매개변수에 @RequestBody를 적용하면 HTTP 요청 바디의 데이터가 바인딩된다.
    //   - @ResponseBody가 적용된 핸들러 메소드는 반환 데이터를 HTTP 응답 바디에 포함시킨다.
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
