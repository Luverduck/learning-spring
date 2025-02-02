package springmvc.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 메소드 실행
        printRequestLine(request);
        printHeaders(request);
        printHeader(request);
        printEtc(request);
    }

    // HTTP 요청 시작 라인 출력
    private void printRequestLine(HttpServletRequest request) {
        System.out.println("--- Request Line START ---");

        // HTTP 메소드
        System.out.println("request.getMethod() = " + request.getMethod());
        // 요청에 사용된 HTTP 프로토콜의 종류와 버전
        System.out.println("request.getProtocol() = " + request.getProtocol());
        // 요청에 사용된 HTTP 프로토콜의 종류 (http / https)
        System.out.println("request.getScheme() = " + request.getScheme());
        // 요청 URL
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // 요청 URI
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        // 요청에 포함된 쿼리 스트링 반환
        System.out.println("request.getQueryString() = " + request.getQueryString());
        // HTTPS 사용 여부
        System.out.println("request.isSecure() = " + request.isSecure());

        System.out.println("--- Request Line END ---");
        System.out.println();
    }

    // HTTP 요청 헤더 전체 출력
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers START ---");

        // HTTP 요청 헤더 단일
        // System.out.println("request.getHost() = " + request.getHeader("Host"));

        /*
        // HTTP 요청 헤더 전체
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + " : " + request.getHeader(headerName));
        }
        */

        // HTTP 요청 헤더 전체
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + " : " + request.getHeader(headerName)));

        System.out.println("--- Headers END ---");
        System.out.println();
    }

    // HTTP 요청 헤더 출력
    private void printHeader(HttpServletRequest request) {
        System.out.println("--- Header START ---");

        // HTTP 요청을 처리하는 서버의 호스트 정보
        System.out.println("[Host]");
        // 요청을 처리하는 서버의 호스트 이름
        System.out.println("request.getServerName() = " + request.getServerName());
        // 요청을 처리하는 서버의 포트 번호
        System.out.println("request.getServerPort() = " + request.getServerPort());
        System.out.println();

        // HTTP 요청을 보낸 클라이언트가 선호하는 언어 정보
        System.out.println("[Accept-Language]");
        // 클라이언트가 선호하는 언어 목록과 순서
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        // 클라이언트가 가장 선호하는 언어
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();

        // HTTP 요청에 포함된 쿠키 목록
        System.out.println("[cookie]");
        // 요청에 포함된 모든 쿠키 반환
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        // HTTP 요청 바디 정보
        System.out.println("[Content]");
        // HTTP 요청 바디의 콘텐츠 타입 (MIME)
        System.out.println("request.getContentType() = " + request.getContentType());
        // HTTP 요청 바디의 콘텐츠 길이
        System.out.println("request.getContentLength() = " + request.getContentLength());
        // HTTP 요청 바디에 사용된 문자 인코딩 방식
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- Header END ---");
        System.out.println();
    }

    // HTTP 요청 헤더 기타 정보 출력
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 정보 START ---");

        // HTTP 요청을 보낸 클라이언트의 호스트 정보
        System.out.println("[Remote]");
        // 요청을 보낸 클라이언트의 호스트 이름
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        // 요청을 보낸 클라이언트의 IP 주소
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        // 요청을 보낸 클라이언트의 포트 번호
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        // HTTP 요청을 받는 서버의 로컬 호스트 정보
        System.out.println("[Local]");
        // 요청을 받는 서버의 로컬 호스트 이름
        System.out.println("request.getLocalName() = " + request.getLocalName());
        // 요청을 받는 서버의 로컬 IP 주소
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        // 요청을 받는 서버의 로컬 포트 번호
        System.out.println("request.getLocalPort() = " + request.getLocalPort());

        System.out.println("--- 기타 정보 END ---");
        System.out.println();
    }
}
