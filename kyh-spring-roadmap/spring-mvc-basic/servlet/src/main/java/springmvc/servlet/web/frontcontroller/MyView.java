package springmvc.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MyView {

    // 뷰 경로
    private String viewPath;

    // 생성자
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 뷰 랜더링
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request와 response를 지정된 뷰로 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    // 뷰 랜더링
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 모델의 데이터를 request에 추가
        changeModelToRequestAttribute(model, request);
        // request와 response를 지정된 뷰로 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    // 모델의 데이터를 request에 추가
    private void changeModelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
