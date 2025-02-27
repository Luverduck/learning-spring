package springmvc.servlet.web.frontcontroller.v3;

import springmvc.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // HTTP 요청 처리
    ModelView process(Map<String, String> paramMap);
}
