package springdb.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springdb.jdbc.domain.Member;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static springdb.jdbc.connection.ConnectionConst.*;

@Slf4j
class MemberRepositoryV1Test {

    // 회원 저장소
    MemberRepositoryV1 repository;

    // 매 번 테스트 실행 전에 호출할 메소드
    @BeforeEach
    void beforeEach() {
        // DataSource를 주입하여 회원 저장소 생성
        // 1) DriverManagerDataSource
        // DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        // 2) HikaryDataSource
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPoolName(PASSWORD);
        repository = new MemberRepositoryV1(dataSource);
    }

    @Test
    void crud() throws SQLException, InterruptedException {
        // 회원 정보 설정
        Member member = new Member("memberV5", 10000);

        // save() 테스트
        repository.save(member);

        // findById() 테스트
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        log.info("member equals findMember {}", member.equals(findMember));
        // save()에 사용한 회원 정보와 findById()로 반환환 회원 정보가 같은지 확인
        assertThat(findMember).isEqualTo(member);

        // update() 테스트
        repository.update(member.getMemberId(), 20000);
        Member updatedMember = repository.findById(member.getMemberId());
        // update()에 사용한 회원 정보와 findById()로 반환환 회원 정보가 같은지 확인
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        // delete() 테스트
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId())).isInstanceOf(NoSuchElementException.class) ;

        // 커넥션 풀에서 커넥션이 생성되는 로그를 출력하기 위함
        Thread.sleep(1000);
    }
}