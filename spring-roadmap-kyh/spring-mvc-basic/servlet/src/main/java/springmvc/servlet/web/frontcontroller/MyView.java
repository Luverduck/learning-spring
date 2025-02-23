package springmvc.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MyView {

    // 뷰 경로
    private String viewPath;

    // 생성자
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 뷰 랜더링
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 클라이언트의 요청을 지정된 뷰로 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
