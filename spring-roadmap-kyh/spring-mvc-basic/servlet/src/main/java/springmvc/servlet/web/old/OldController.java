package springmvc.servlet.web.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component("/springmvc/old-controller") // 스프링 빈의 이름을 URL로 설정
public class OldController implements Controller {

    // 요청 처리 메소드
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        // ModelAndView를 통해 뷰의 이름 반환
        return new ModelAndView("new-form");
    }
}
