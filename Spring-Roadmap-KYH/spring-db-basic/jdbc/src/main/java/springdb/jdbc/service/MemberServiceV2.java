package springdb.jdbc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springdb.jdbc.domain.Member;
import springdb.jdbc.repository.MemberRepositoryV2;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - Connection을 인수로 전달
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    // 계좌 이체
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        // 커넥션 생성
        Connection con =  dataSource.getConnection();
        try {
            // 트랜잭션 시작
            con.setAutoCommit(false);
            // 비즈니스 로직 실행
            businessLogic(fromId, toId, money, con);
            // 트랜잭션 커밋
            con.commit();
        } catch(Exception e) {
            // 트랜잭션 롤백
            con.rollback();
            throw new IllegalStateException(e);
        } finally {
            // 커넥션 해제
            release(con);
        }
    }

    // 비즈니스 로직
    private void businessLogic(String fromId, String toId, int money, Connection con) throws SQLException {
        // 회원 조회
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);
        // fromMember의 잔고 감소
        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        // toMember의 ID가 ex이면 예외 발생
        validation(toMember);
        // toMember의 잔고 증가
        memberRepository.update(con, toId, toMember.getMoney() + money);
    }

    // 커넥션 해제
    private void release(Connection con) {
        if(con != null) {
            try {
                // 자동 커밋 모드로 변경한 후 커넥션 반환
                con.setAutoCommit(true);
                con.close();
            } catch(Exception e) {
                log.info("error", e);
            }
        }
    }

    // 이체 대상의 유효성 검사
    private void validation(Member toMember) {
        // 이체 대상의 ID가 ex이면 예외 발생
        if(toMember.getMemberId().equals("ex"))
            throw new IllegalStateException("이체 중 예외 발생");
    }
}
