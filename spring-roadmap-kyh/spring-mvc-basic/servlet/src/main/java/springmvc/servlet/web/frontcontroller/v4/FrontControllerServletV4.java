package springmvc.servlet.web.frontcontroller.v4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.web.frontcontroller.ModelView;
import springmvc.servlet.web.frontcontroller.MyView;
import springmvc.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import springmvc.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import springmvc.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    // 매핑 정보
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    // 생성자
    public FrontControllerServletV4() {
        // 프론트 컨트롤러를 생성할 때 HTTP 요청의 URL에 따라 해당 요청을 처리할 컨트롤러를 지장한다.
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    // 클라이언트의 요청을 받아 해당 요청을 처리할 컨트롤러 조회 및 호출
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 요청에서 URL 반환
        String requestURI = request.getRequestURI();
        // 매핑 정보에서 해당 URL의 요청을 처리할 컨트롤러 조회
        ControllerV4 controller = controllerMap.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 모든 요청 매개변수를 Map으로 반환
        Map<String, String> paramMap = createParamMap(request);
        // 모델 생성
        Map<String, Object> model = new HashMap<>();
        // 조회한 컨트롤러의 HTTP 요청 처리 메소드 실행 후 뷰의 이름 반환
        String viewName = controller.process(paramMap, model);
        // 반환된 뷰의 이름을 통해 실제 뷰(JSP)의 경로 반환
        MyView view = viewResolver(viewName);
        // 뷰 렌더링 메소드 실행
        view.render(model, request, response);
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
