package springmvc.servlet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.web.frontcontroller.MyView;
import springmvc.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import springmvc.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import springmvc.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    // 매핑 정보
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 생성자
    public FrontControllerServletV2() {
        // 프론트 컨트롤러를 생성할 때 HTTP 요청의 URL에 따라 해당 요청을 처리할 컨트롤러를 지장한다.
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    // 클라이언트의 요청을 받아 해당 요청을 처리할 컨트롤러 조회 및 호출
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 요청에서 URL 반환
        String requestURI = request.getRequestURI();
        // 매핑 정보에서 해당 URL의 요청을 처리할 컨트롤러 조회
        ControllerV2 controller = controllerMap.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 조회한 컨트롤러의 HTTP 요청 처리 메소드 실행 후 뷰 반환
        MyView view = controller.process(request, response);
        // 뷰를 통해 HTML 문서를 생성
        view.render(request, response);
    }
}
