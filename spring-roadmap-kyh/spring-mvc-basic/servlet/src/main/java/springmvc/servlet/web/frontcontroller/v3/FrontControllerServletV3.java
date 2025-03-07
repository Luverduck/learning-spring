package springmvc.servlet.web.frontcontroller.v3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.web.frontcontroller.ModelView;
import springmvc.servlet.web.frontcontroller.MyView;
import springmvc.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import springmvc.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import springmvc.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    // 매핑 정보
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    // 생성자
    public FrontControllerServletV3() {
        // 프론트 컨트롤러를 생성할 때 HTTP 요청의 URL에 따라 해당 요청을 처리할 컨트롤러를 지장한다.
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    // 클라이언트의 요청을 받아 해당 요청을 처리할 컨트롤러 조회 및 호출
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 요청에서 URL 반환
        String requestURI = request.getRequestURI();
        // 매핑 정보에서 해당 URL의 요청을 처리할 컨트롤러 조회
        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 모든 요청 매개변수를 Map으로 반환
        Map<String, String> paramMap = createParamMap(request);
        // 조회한 컨트롤러의 HTTP 요청 처리 메소드 실행 후 모델과 뷰의 이름 반환
        ModelView mv = controller.process(paramMap);
        // 반환된 뷰의 이름을 통해 실제 뷰(JSP)의 경로 반환
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        // 뷰 렌더링 메소드 실행
        view.render(mv.getModel(), request, response);
    }

    // 모든 요청 매개변수를 Map으로 반환 (매개변수 맵)
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    // 뷰의 이름을 통해 실제 뷰의 경로 반환
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
