package springmvc.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    // 회원 ID
    private Long id;
    // 회원 이름
    private String username;
    // 회원 나이
    private int age;

    // 생성자
    public Member() {

    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
