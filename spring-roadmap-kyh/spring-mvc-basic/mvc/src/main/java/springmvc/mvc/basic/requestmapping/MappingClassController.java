package springmvc.mvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * 회원 목록 조회
     * GET '/mapping/users'
     */
    @GetMapping
    public String user() {
        return "get users";
    }

    /**
     * 회원 등록
     * POST '/mapping/users'
     */
    @PostMapping
    public String addUser() {
        return "post users";
    }

    /**
     * 회원 조회
     * GET '/mapping/users/{userId}'
     */
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get userId=" + userId;
    }

    /**
     * 회원 수정
     * PATCH '/mapping/users/{userId}'
     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId=" + userId;
    }

    /**
     * 회원 삭제 :
     * DELETE '/mapping/users/{userId}'
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId=" + userId;
    }
}
