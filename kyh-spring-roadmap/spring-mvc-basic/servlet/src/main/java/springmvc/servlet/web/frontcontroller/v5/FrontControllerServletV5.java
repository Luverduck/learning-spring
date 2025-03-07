package springmvc.servlet.web.frontcontroller.v5;

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
import springmvc.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import springmvc.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import springmvc.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import springmvc.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import springmvc.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 핸들러 매핑 정보
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    // 핸들러 어댑터 목록
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    // 생성자
    public FrontControllerServletV5() {
        // 핸들러 매핑 정보 초기화
        initHandlerMappingMap();
        // 핸들러 어댑터 목록 초기화
        initHandlerAdapters();
    }

    // 핸들러 매핑 정보 초기화
    private void initHandlerMappingMap() {
        // HTTP 요청 URL과 해당 요청을 처리할 ControllerV3 타입 핸들러(컨트롤러)
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        // HTTP 요청 URL과 해당 요청을 처리할 ControllerV4 타입 핸들러(컨트롤러)
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    // 핸들러 어댑터 목록 초기화
    private void initHandlerAdapters() {
        // ControllerV3에 대한 핸들러 어댑터
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        // ControllerV4에 대한 핸들러 어댑터
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 핸들러 매핑 정보에서 해당 URL의 요청을 처리할 핸들러 반환
        Object handler = getHandler(request);
        if(handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 핸들러 어댑터 목록에서 해당 핸들러와 호환되는 핸들러 어댑터 반환
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        // 핸들러 어댑터를 통해 핸들러의 요청 처리 메소드 실행 후 모델과 뷰의 이름 반환
        ModelView mv = adapter.handle(request, response, handler);
        // 반환된 뷰의 이름을 통해 뷰 객체 반환
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        // 뷰 렌더링 메소드 실행
        view.render(mv.getModel(), request, response);
    }

    // 핸들러 조회
    private Object getHandler(HttpServletRequest request) {
        // HTTP 요청에서 URL 반환
        String requestURI = request.getRequestURI();
        // 핸들러 매핑 정보에서 해당 URL의 요청을 처리할 핸들러(컨트롤러) 조회
        return handlerMappingMap.get(requestURI);
    }

    // 핸들러 어댑터 조회
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        // 헌들러 어댑터 목록에서 해당 핸들러와 호환되는 핸들러 어댑터 반환
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = " + handler);
    }

    // 뷰 반환
    private MyView viewResolver(String viewName) {
        // 뷰의 이름을 통해 실제 뷰의 경로 반환
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
