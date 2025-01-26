package springcore.basic.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springcore.basic.common.MyLogger;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        
        // 요청을 받는 시점에 myLogger의 클래스명 출력
        System.out.println(myLogger.getClass());

        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        Thread.sleep(1000);

        logDemoService.logic("testId");
        return "OK";
    }
}
