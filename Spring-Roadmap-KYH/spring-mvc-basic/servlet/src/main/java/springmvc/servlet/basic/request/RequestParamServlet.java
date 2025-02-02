package springmvc.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * URL의 쿼리 스트링을 통해 데이터 전달
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 모든 쿼리 파라미터 조회
        // http://localhost:8080/request-param?username=hello&age=20
        System.out.println("[모든 쿼리 파라미터 조회] - START");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println("[모든 쿼리 파라미터 조회] - END");
        System.out.println();

        // 단일 쿼리 파라미터 조회
        // http://localhost:8080/request-param?username=hello&age=20
        System.out.println("[단일 쿼리 파라미터 조회] - START");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 쿼리 파라미터 조회] - END");
        System.out.println();

        // 동일한 이름의 쿼리 파라미터가 여러 개인 경우
        // http://localhost:8080/request-param?username=hello&username=spring&username=java
        System.out.println("[동일한 이름의 쿼리 파라미터가 여러 개인 경우] - START");
        String[] usernames = request.getParameterValues("username");
        for(var name : usernames)
            System.out.println("name = " + name);
        System.out.println("[동일한 이름의 쿼리 파라미터가 여러 개인 경우] - END");
        System.out.println();
    }
}
