package springmvc.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 응답 헤더 설정
        response.setContentType("text/html"); // HTML 문서
        // response.setContentType("text/plain"); // 단순 텍스트
        response.setCharacterEncoding("utf-8");
        // HTTP 응답 바디 설정
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("    <div>안녕하세요</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
