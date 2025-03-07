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
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members/")
public class MemberListServlet extends HttpServlet {

    // 회원 저장소
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 회원 저장소에 저장된 모든 회원 정보 반환
        List<Member> members = memberRepository.findAll();
        // 응답 바디의 컨텐츠 타입을 HTML 문서로 설정
        response.setContentType("text/html");
        // 응답 바디의 문자 인코딩 방식을 UTF-8로 설정
        response.setCharacterEncoding("utf-8");
        // 응답 바디 작성
        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write("   <meta charset=\"UTF-8\">");
        w.write("   <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("   <a href=\"/index.html\">메인</a>");
        w.write("   <table>");
        w.write("       <thead>");
        w.write("           <th>id</th>");
        w.write("           <th>username</th>");
        w.write("           <th>age</th>");
        w.write("       </thead>");
        w.write("       <tbody>");
        for (Member member : members) {
            w.write("   <tr>");
            w.write("       <td>" + member.getId() + "</td>");
            w.write("       <td>" + member.getUsername() + "</td>");
            w.write("       <td>" + member.getAge() + "</td>");
            w.write("   </tr>");
        }
        w.write("       </tbody>");
        w.write("   </table>");
        w.write("</body>");
        w.write("</html>");
    }
}
