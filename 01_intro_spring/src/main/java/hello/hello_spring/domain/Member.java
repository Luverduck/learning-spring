package hello.hello_spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// [06-05] START
// @Entity : JPA가 관리하는 Entity임을 나타낸다.
@Entity
// [06-05] END
public class Member {

    // Field
    // [06-05] START
    // @Id : 데이터베이스의 기본키를 해당 컬럼과 매핑
    // @GeneratedValue : 기본키 값에 대한 생성 전략 설정
    //   - GenerationType.IDENTITY : 데이터베이스에 따라 자동으로 생성
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // [06-05] END

    private String name;

    // Getter / Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}