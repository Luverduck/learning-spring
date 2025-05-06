package springdb.jdbc.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import springdb.jdbc.domain.Member;
import springdb.jdbc.repository.MemberRepository;
import springdb.jdbc.repository.MemberRepositoryV5;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
class MemberServiceV4Test {

    // 회원 ID
    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    // 회원 저장소
    @Autowired
    private MemberRepository memberRepository;
    // 회원 서비스
    @Autowired
    private MemberServiceV4 memberService;

    // 테스트에서 스프링 컨테이너에 등록할 스프링 빈 정의
    @TestConfiguration
    static class TestConfig {
        // DataSource
        private final DataSource dataSource;
        // DataSource의 생성자 주입
        public TestConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }
        @Bean
        MemberRepository memberRepository() {
            // 07.02. 런타임 예외 적용 - 검사 예외를 체이닝하여 비검사 예외로 던지기
            // return new MemberRepositoryV4_1(dataSource);
            // 07.05. 스프링 예외 추상화 적용 - 예외 변환기 적용
            // return new MemberRepositoryV4_2(dataSource);
            // 07.06. JDBC 반복 문제 해결 - JdbcTemplate 적용
            return new MemberRepositoryV5(dataSource);
        }
        @Bean
        MemberServiceV4 memberServiceV4() {
            return new MemberServiceV4(memberRepository());
        }
    }

    // 매 번 테스트 후 실행
    @AfterEach
    void after() {
        // 테스트 데이터 삭제
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }

    // 트랜잭션 AOP 적용 확인
    @Test
    void AopCheck() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberRepository class={}", memberRepository.getClass());
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer() {
        // 로직 실행 전 테스트 데이터 저장
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        // 로직 실행
        log.info("[START] Transaction");
        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);
        log.info("[END] Transaction");

        // 로직 실행 후 결과 확인
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());
        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체 중 예외 발생")
    void accountTransferException() {
        // 로직 실행 전 테스트 데이터 저장
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberEX = new Member(MEMBER_EX, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberEX);

        // 로직 실행
        log.info("[START] Transaction");
        assertThatThrownBy(
                () -> memberService.accountTransfer(memberA.getMemberId(), memberEX.getMemberId(), 2000)
        ).isInstanceOf(IllegalStateException.class);
        log.info("[END] Transaction");

        // 로직 실행 후 결과 확인
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberEX = memberRepository.findById(memberEX.getMemberId());
        assertThat(findMemberA.getMoney()).isEqualTo(10000);
        assertThat(findMemberEX.getMoney()).isEqualTo(10000);
    }
}