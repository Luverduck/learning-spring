package springdb.jdbc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import springdb.jdbc.domain.Member;
import springdb.jdbc.repository.MemberRepositoryV3;

import java.sql.SQLException;

/**
 * 트랜잭션 - @Transactional
 */
@Slf4j
public class MemberServiceV3_3 {

    private final MemberRepositoryV3 memberRepository;

    // 생성자
    public MemberServiceV3_3(MemberRepositoryV3 memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 계좌 이체
    @Transactional
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        // 비즈니스 로직 실행
        businessLogic(fromId, toId, money);
    }

    // 비즈니스 로직
    private void businessLogic(String fromId, String toId, int money) throws SQLException {
        // 회원 조회
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);
        // fromMember의 잔고 감소
        memberRepository.update(fromId, fromMember.getMoney() - money);
        // toMember의 ID가 ex이면 예외 발생
        validation(toMember);
        // toMember의 잔고 증가
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    // 이체 대상의 유효성 검사
    private void validation(Member toMember) {
        // 이체 대상의 ID가 ex이면 예외 발생
        if(toMember.getMemberId().equals("ex"))
            throw new IllegalStateException("이체 중 예외 발생");
    }
}
