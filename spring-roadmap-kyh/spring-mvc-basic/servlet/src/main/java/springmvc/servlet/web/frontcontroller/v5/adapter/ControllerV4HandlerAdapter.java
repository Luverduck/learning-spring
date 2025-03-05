package springmvc.servlet.web.frontcontroller.v5.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.web.frontcontroller.ModelView;
import springmvc.servlet.web.frontcontroller.v4.ControllerV4;
import springmvc.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    // 핸들러 어댑터가 핸들러와 호환되는지 여부
    @Override
    public boolean supports(Object handler) {
        // 핸들러 어댑터가 ControllerV4 타입인지 여부 반환
        return (handler instanceof ControllerV4);
    }

    // 핸들러 어댑터를 통해 핸들러의 요청 처리 메소드를 실행
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // 핸들러를 ControllerV4 타입으로 변환
        ControllerV4 controller = (ControllerV4) handler;
        // 모든 요청 매개변수를 Map으로 반환
        Map<String, String> paramMap = createParamMap(request);
        // 모델 생성
        Map<String, Object> model = new HashMap<>();
        // ControllerV4 타입의 요청 처리 메소드 실행 후 뷰의 이름 반환
        String viewName = controller.process(paramMap, model);
        // ModelView 객체를 생성한 후 뷰의 이름과 모델을 저장
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);
        // ModelView 객체를 프론트 컨트롤러로 반환
        return mv;
    }

    // 요청 매개변수 맵 생성
    private Map<String, String> createParamMap(HttpServletRequest request) {
        // 모든 요청 매개변수를 Map으로 반환
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
