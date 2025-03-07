package springmvc.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;
import springmvc.servlet.basic.HelloData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // ObjectMapper 생성
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 요청 바디를 읽는 입력 스트림
        ServletInputStream inputStream = request.getInputStream();
        // StreamUtils을 통해 입력 스트림을 UTF-8 방식으로 모두 읽고 문자열로 반환
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // HTTP 요청 바디에서 반환한 데이터 출력
        System.out.println("messageBody = " + messageBody);

        // HTTP 요청 바디에서 JSON 데이터를 읽고 자바 객체로 반환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        // HTTP 요청 바디에서 반환한 객체의 데이터 출력
        System.out.println("helloData.getUsername = " + helloData.getUsername());
        System.out.println("helloData.getAge = " + helloData.getAge());

        response.getWriter().write("ok");
    }
}
