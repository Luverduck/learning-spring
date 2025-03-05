package springmvc.servlet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.web.frontcontroller.ModelView;

import java.io.IOException;

public interface MyHandlerAdapter {

    // 핸들러 어댑터가 핸들러와 호환되는지 여부
    boolean supports(Object handler);

    // 핸들러 어댑터를 통해 핸들러의 요청 처리 메소드를 실행
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
