package springmvc.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseTextServlet", urlPatterns = "/response-text")
public class ResponseTextServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 응답 헤더 설정
        response.setContentType("text/plain"); // 단순 텍스트
        response.setCharacterEncoding("utf-8");
        // HTTP 응답 바디 설정
        PrintWriter writer = response.getWriter();
        // HTTP 응답 바디에 쓰기 후 줄 바꿈하지 않음
        writer.write("안녕하세요");
        writer.write("안녕하세요");
        // HTTP 응답 바디에 쓰기 후 줄 바꿈함
        writer.println("안녕하세요");
        writer.println("안녕하세요");
    }
}
