package springmvc.servlet.web.frontcontroller.v5.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.web.frontcontroller.ModelView;
import springmvc.servlet.web.frontcontroller.v3.ControllerV3;
import springmvc.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    // 핸들러 어댑터가 핸들러와 호환되는지 여부
    @Override
    public boolean supports(Object handler) {
        // 핸들러 어댑터가 ControllerV3 타입인지 여부 반환
        return (handler instanceof ControllerV3);
    }

    // 핸들러 어댑터를 통해 핸들러의 요청 처리 메소드를 실행
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // 핸들러를 ControllerV3 타입으로 변환
        ControllerV3 controller = (ControllerV3) handler;
        // 모든 요청 매개변수를 Map으로 반환
        Map<String, String> paramMap = createParamMap(request);
        // ControllerV3 타입의 요청 처리 메소드 실행 후 ModelView 객체 반환
        ModelView mv = controller.process(paramMap);
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
