package springdb.jdbc.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springdb.jdbc.domain.Member;
import springdb.jdbc.repository.MemberRepositoryV1;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static springdb.jdbc.connection.ConnectionConst.*;

/**
 * 기본 동작
 */
class MemberServiceV1Test {

    // 회원 ID
    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    // 회원 저장소
    private MemberRepositoryV1 memberRepository;
    // 회원 서비스
    private MemberServiceV1 memberService;

    // 매 번 테스트 전 실행
    @BeforeEach
    void before() {
        // 커넥션 생성 및 설정
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        memberRepository = new MemberRepositoryV1(dataSource);
        memberService = new MemberServiceV1(memberRepository);
    }

    // 매 번 테스트 후 실행
    @AfterEach
    void after() throws SQLException {
        // 테스트 데이터 삭제
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {
        // 로직 실행 전 테스트 데이터 저장
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_A, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        // 로직 실행
        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);

        // 로직 실행 후 결과 확인
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());
        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체 중 예외 발생")
    void accountTransferException() throws SQLException {
        // 로직 실행 전 테스트 데이터 저장
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberEX = new Member(MEMBER_EX, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberEX);

        // 로직 실행
        assertThatThrownBy(
            () -> memberService.accountTransfer(memberA.getMemberId(), memberEX.getMemberId(), 2000)
        ).isInstanceOf(IllegalStateException.class);

        // 로직 실행 후 결과 확인
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberEX = memberRepository.findById(memberEX.getMemberId());
        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberEX.getMoney()).isEqualTo(10000);
    }
}