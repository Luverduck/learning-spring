package hello.hello_spring;

import hello.hello_spring.repository.JdbcMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// [04-02] START
@Configuration
public class SpringConfig {

    // [06-02] START
    DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    };
    // [06-02] START

    // 회원 레퍼지토리의 생성자 정의
    @Bean
    public MemberRepository memberRepository() {
        // MemoryMemberRepository의 인스턴스 생성 후 반환하여 빈으로 등록
        // return new MemoryMemberRepository();

        // [06-02] START
        return new JdbcMemberRepository(dataSource);
        // [06-02] START
    };
}
// [04-02] END