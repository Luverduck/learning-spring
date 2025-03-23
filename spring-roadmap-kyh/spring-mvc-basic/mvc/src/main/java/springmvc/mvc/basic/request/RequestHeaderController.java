package springmvc.mvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod,
            Locale locale,
            @RequestHeader MultiValueMap<String, String> headerMap,
            @RequestHeader("host") String host,
            @CookieValue(value = "myCookie", required = false) String cookie
    ) {

        // 지정한 타입의 요청 또는 응답 (ServletRequest, ServletResponse 또는 그 하위 타입)
        log.info("request={}", request);
        log.info("response={}", response);
        // HTTP 요청의 메소드
        log.info("httpMethod={}", httpMethod);
        // 요청에 포함된 지역 설정
        log.info("locale={}", locale);
        // 요청 헤더의 모든 값 (Key-Value)
        log.info("headerMap={}", headerMap);
        // 요청 헤더의 특정 값 (Value)
        log.info("header host={}", host);
        // 요청에 포함된 쿠키
        log.info("myCookie={}", cookie);

        return "ok";
    }
}
