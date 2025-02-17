package springmvc.servlet.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.domain.member.MemberRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 응답 바디의 컨텐츠 타입을 HTML 문서로 설정
        response.setContentType("text/html");
        // 응답 바디의 문자 인코딩 방식을 UTF-8로 설정
        response.setCharacterEncoding("utf-8");
        // 응답 바디 작성
        PrintWriter w = response.getWriter();
        w.write(
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<title>Title</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "   <form action=\"/servlet/members/save\" method=\"post\">\n" +
            "       username: <input type=\"text\" name=\"username\" />\n" +
            "       age: <input type=\"text\" name=\"age\" />\n" +
            "       <button type=\"submit\">전송</button>\n" +
            "   </form>\n" +
            "</body>\n" +
            "</html>\n"
        );
    }
}
