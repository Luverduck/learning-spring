package springmvc.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import springmvc.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import springmvc.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // 매핑 정보
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 생성자
    public FrontControllerServletV1() {
        // 프론트 컨트롤러를 생성할 때 HTTP 요청의 URL에 따라 해당 요청을 처리할 컨트롤러를 지장한다.
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    // 클라이언트의 요청을 받아 해당 요청을 처리할 컨트롤러 조회 및 호출
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        // HTTP 요청에서 URL 반환
        String requestURI = request.getRequestURI();
        // 매핑 정보에서 해당 URL의 요청을 처리할 컨트롤러 조회
        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 조회한 컨트롤러의 HTTP 요청 처리 메소드 출력
        controller.process(request, response);
    }
}
