package springmvc.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    // HTTP 요청 처리
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
