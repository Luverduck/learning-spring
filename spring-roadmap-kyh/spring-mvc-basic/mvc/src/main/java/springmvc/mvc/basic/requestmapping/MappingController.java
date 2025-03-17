package springmvc.mvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @RequestMapping의 value
     * 핸들러가 처리할 요청 URL을 지정한다.
     */
    @RequestMapping("/mapping-basic")
    public String mappingBasic() {
        log.info("mappingBasic");
        return "ok";
    }

    /**
     * @RequestMapping의 value 배열
     * 핸들러가 처리할 요청 URL이 여러 개인 경우 배열의 형태로 지정한다.
     */
    @RequestMapping({"/mapping-multi1", "/mapping-multi2"})
    public String mappingMultiURL() {
        log.info("mappingMultiURL");
        return "ok";
    }

    /**
     * @RequestMapping의 method
     * 핸들러가 처리할 HTTP 메소드를 지정한다.
     * GET, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 요청 매핑 어노테이션
     * 핸들러가 처리할 HTTP 메소드를 지정한다.
     * 요청 매핑 어노테이션은 @RequestMapping을 포함한다.
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @PatchMapping
     * @DeleteMapping
     */
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     * 경로 변수 (Path Variable)
     * 요청 URL의 일부를 변수화한다.
     * 요청 URL을 변수화하는 부분을 경로 변수(PathVariable)라 한다.
     * 요청 URL에 포함된 경로 변수는 @PathVraible을 통해 반환할 수 있다.
     * 경로 변수명이 매개변수명과 다를 경우 name의 값을 지정해야 한다.
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * 요청 매핑 어노테이션의 params
     * 요청 매핑을 위해 필요한 요청 매개변수의 목록을 지정한다.
     * params = "debug"
     * params = "!debug"
     * params = "mode=debug"
     * params = "mode!=debug"
     * params = {"mode=debug", "data=good"}
     */
    @GetMapping(value = "/mapping-params", params = "mode=debug")
    public String mappingParams() {
        log.info("mappingParams");
        return "ok";
    }

    /**
     * 요청 매핑 어노테이션의 headers
     * 요청 매핑을 위해 필요한 요청 헤더의 목록을 지정한다.
     * headers = "debug"
     * headers = "!debug"
     * headers = "mode=debug"
     * headers = "mode!=debug"
     * headers = {"mode=debug", "data=good"}
     */
    @GetMapping(value = "/mapping-headers", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * 요청 매핑 어노테이션의 consumes
     * 요청 매핑을 위해 필요한 요청 본문의 컨텐츠 타입을 지정한다.
     */
    @PostMapping(value = "/mapping-consumes", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * 요청 매핑 어노테이션의 produces
     * 요청 매핑을 위해 필요한 응답 본문의 컨텐츠 타입을 지정한다.
     */
    @PostMapping(value = "/mapping-produces", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}