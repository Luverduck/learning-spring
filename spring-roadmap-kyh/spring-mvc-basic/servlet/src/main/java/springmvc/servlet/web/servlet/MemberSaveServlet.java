package springmvc.servlet.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청으로부터 요청 파라미터 반환
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        // 회원 저장소에 회원 정보 저장
        Member member = new Member(username, age);
        memberRepository.save(member);
        // 응답 바디의 컨텐츠 타입을 HTML 문서로 설정
        response.setContentType("text/html");
        // 응답 바디의 문자 인코딩 방식을 UTF-8로 설정
        response.setCharacterEncoding("utf-8");
        // 응답 바디 작성
        PrintWriter w = response.getWriter();
        w.write(
            "<html>\n" +
            "<head>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "</head>\n" +
            "<body>\n" +
            "   성공\n" +
            "   <ul>\n" +
            "       <li>id="+member.getId()+"</li>\n" +
            "       <li>username="+member.getUsername()+"</li>\n" +
            "       <li>age="+member.getAge()+"</li>\n" +
            "   </ul>\n" +
            "   <a href=\"/index.html\">메인</a>\n" +
            "</body>\n" +
            "</html>"
        );
    }
}
