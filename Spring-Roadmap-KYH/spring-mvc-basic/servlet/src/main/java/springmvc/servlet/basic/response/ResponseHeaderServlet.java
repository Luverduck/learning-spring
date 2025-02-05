package springmvc.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 응답 시작 라인 설정
        response.setStatus(HttpServletResponse.SC_OK);

        // HTTP 응답 헤더 설정
        // 콘텐츠 타입 (타입, 인코딩 방식)
        // response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // 콘텐츠 길이
        // response.setContentLength(2); // 생략 시 자동 생성
        // 캐시 설정 (HTTP/1.1) : 브라우저나 캐시 서버에서 클라이언트의 요청에 대해 기존 요청에서 캐싱된 응답을 사용할 지 여부
        // - no-cache : 캐싱된 응답을 사용하지 않도록 설정
        // - no-store : 응답을 캐싱하지 않도록 설정
        // - must-revalidate : 캐싱된 응답을 사용하기 전에 서버에서 응답을 재검증하도록 설정
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // 캐시 설정 (HTTP/1.0) : 브라우저나 캐시 서버에서 클라이언트의 요청에 대해 기존 요청에서 캐싱된 응답을 사용할 지 여부
        // - no-cache : 캐싱된 응답을 사용하지 않도록 설정
        response.setHeader("Pragma", "no-cache");
        // 사용자 지정 헤더
        response.setHeader("Custom-Header", "hello");

        // 쿠키 설정
        // - name=value : 쿠키의 이름(name)과 값(value)
        // - Max-Age : 쿠키의 수명[초]
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie myCookie = new Cookie("myCookie", "good");
        myCookie.setMaxAge(600);
        response.addCookie(myCookie);

        // 리다이렉트
        // response.setStatus(HttpServletResponse.SC_FOUND);
        // response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");

        // HTTP 응답 바디 설정
        PrintWriter writer = response.getWriter();
        writer.write("ok");
    }
}
