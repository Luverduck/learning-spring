package springmvc.mvc.basic.response;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springmvc.mvc.basic.HelloData;

import java.io.IOException;
import java.io.Writer;

@Slf4j
@Controller
public class ResponseBodyController {

    // 서블릿의 출력 스트림 사용
    // 서블릿에서 출력 스트림을 반환하여 HTTP 요청 바디에 데이터를 입력할 수 있다.
    // - 출력 스트림 : OutputStream, Writer
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        // 서블릿에서 출력 스트림 반환
        Writer writer = response.getWriter();
        // 반환한 출력 스트림으로 HTTP 응답 바디에 데이터 쓰기
        writer.write("ok");
    }

    // @ResponseBody 사용
    // @ResponseBody가 적용된 핸들러 메소드는 반환 데이터를 HTTP 응답 바디에 포함시킨다.
    // @ResponseBody가 클래스에 적용될 경우 해당 클래스 안의 모든 핸들러 메소드는 반환 데이터를 HTTP 응답 바디에 포함시킨다.
    @ResponseBody
    @GetMapping("/response-body-string-v2")
    public String responseBodyV2() {
        return "ok";
    }

    // HttpEntity<T> 사용
    // HTTP 메시지 전체를 추상화한 클래스로 HTTP 요청/응답의 모든 헤더와 바디 정보를 포함한다.
    // HttpEntity<String> 객체를 반환하면 해당 객체의 데이터를 HTTP 응답 바디에 포함시킨다.
    // 핸들러 메소드에 @ResponseStatus를 적용하여 응답의 상태 코드를 지정할 수 있다.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/response-body-string-v3")
    public HttpEntity<String> responseBodyV3() {
        // HttpEntity<String> 객체에 HTTP 응답 바디의 데이터 설정
        return new HttpEntity<>("ok");
    }

    // HttpEntity<T> 사용 - ResponseEntity<T>
    // ResponseEntity<T>에 HTTP 응답 바디의 데이터를 설정하고 응답의 상태 코드를 지정할 수 있다.
    @GetMapping("/response-body-string-v4")
    public ResponseEntity<String> responseBodyV4() {
        // ResponseEntity<String> 객체에 HTTP 응답 바디의 데이터 설정 및 응답의 상태 코드 지정
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    // @ResponseBody 사용
    // @ResponseBody가 적용된 핸들러 메소드는 반환 데이터를 HTTP 응답 바디에 포함시킨다.
    // @ResponseBody가 적용된 핸들러 메소드에서 Java 객체를 반환할 경우
    // HTTP 메시지 컨버터에서 Java 객체의 정보를 JSON 데이터로 변환하여 HTTP 응답 바디에 포함시킨다.
    // - HTTP 응답의 상태 코드를 지정하기 위해서는 @ResponseStatus를 사용해야 한다.
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v1")
    public HelloData responseBodyJsonV1() {
        // 반환할 객체 설정
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        // 객체를 직접 반환
        return helloData;
    }

    // HttpEntity<T> 사용
    // HTTP 메시지 전체를 추상화한 클래스로 HTTP 요청/응답의 모든 헤더와 바디 정보를 포함한다.
    // HttpEntity<T> 객체를 반환하면 HTTP 메시지 컨버터에서 해당 객체의 정보를 JSON 데이터로 변환하여 HTTP 응답 바디에 포함시킨다.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/response-body-json-v2")
    public HttpEntity<HelloData> responseBodyJsonV2() {
        // 반환할 객체 설정
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        // 객체를 HttpEntity<T> 형태로 반환
        return new HttpEntity<>(helloData);
    }

    // HttpEntity<T> 사용 - ResponseEntity<T>
    // ResponseEntity<T>에 HTTP 응답 바디의 데이터를 설정하고 응답의 상태 코드를 지정할 수 있다.
    @GetMapping("/response-body-json-v3")
    public ResponseEntity<HelloData> responseBodyJsonV3() {
        // 반환할 객체 설정
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        // 객체를 응답의 상태 코드와 함께 HttpEntity<T> 형태로 반환
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }
}
