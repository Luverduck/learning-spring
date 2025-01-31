package springmvc.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // HttpServlet의 service()를 오버라이딩
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 서블릿 등록과 요청 매핑
        System.out.println("HelloServlet.service");
        // request 출력
        System.out.println("request = " + request);
        // response 출력
        System.out.println("response = " + response);

        // HTTP 요청에서 쿼리 파라미터 반환
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // HTTP 응답 설정
        // HTTP 응답 헤더 설정
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // HTTP 응답 바디에 메시지 작성
        response.getWriter().write("Hello, " + username);
    }
}
