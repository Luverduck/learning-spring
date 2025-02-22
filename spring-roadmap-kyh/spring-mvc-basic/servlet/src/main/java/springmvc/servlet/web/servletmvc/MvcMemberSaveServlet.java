package springmvc.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.domain.member.Member;
import springmvc.servlet.domain.member.MemberRepository;

import java.io.IOException;

@WebServlet(name = "mvcMemberServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

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
        // 회원 저장소에 저장된 회원 정보를 Attribute에 저장
        request.setAttribute("member", member);
        // 클라이언트의 요청을 처리할 JSP 경로
        String viewPath = "/WEB-INF/views/save-result.jsp";
        // 클라이언트의 요청을 지정한 JSP로 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
