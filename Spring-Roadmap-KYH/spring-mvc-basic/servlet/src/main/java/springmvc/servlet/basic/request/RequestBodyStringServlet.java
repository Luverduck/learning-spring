package springmvc.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HTML 요청 바디를 통해 데이터 전달 (단순 텍스트)
 */
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 요청 바디를 읽는 입력 스트림
        ServletInputStream inputStream = request.getInputStream();
        // StreamUtils을 통해 입력 스트림을 UTF-8 방식으로 모두 읽고 문자열로 반환
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // HTTP 요청 바디 출력
        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("ok");
    }
}
