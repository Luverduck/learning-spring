package springdb.jdbc.service;

import lombok.RequiredArgsConstructor;
import springdb.jdbc.domain.Member;
import springdb.jdbc.repository.MemberRepositoryV1;

import java.sql.SQLException;

@RequiredArgsConstructor
public class MemberServiceV1 {

    private final MemberRepositoryV1 memberRepository;

    // 계좌 이체
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
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
