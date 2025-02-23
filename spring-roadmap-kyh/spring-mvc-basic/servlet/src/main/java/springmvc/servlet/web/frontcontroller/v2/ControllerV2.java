package springmvc.servlet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springmvc.servlet.web.frontcontroller.MyView;

import java.io.IOException;

public interface ControllerV2 {

    // HTTP 요청 처리
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
