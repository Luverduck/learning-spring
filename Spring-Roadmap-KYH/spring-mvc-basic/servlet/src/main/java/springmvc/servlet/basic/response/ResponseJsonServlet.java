package springmvc.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.basic.HelloData;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    // ObjectMapper 생성
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 응답 헤더 설정
        response.setContentType("application/json"); // JSON
        //response.setCharacterEncoding("utf-8");
        // 객체 생성 및 초기화
        HelloData helloData = new HelloData();
        helloData.setUsername("eom");
        helloData.setAge(20);
        // 객체를 JSON 문자열로 변환
        String result = objectMapper.writeValueAsString(helloData);
        // HTTP 응답 바디 설정
        // response.getWriter().write(result); // 인코딩 방식을 utf-8로 명시해야 함
        response.getOutputStream().write(result.getBytes());
    }
}
