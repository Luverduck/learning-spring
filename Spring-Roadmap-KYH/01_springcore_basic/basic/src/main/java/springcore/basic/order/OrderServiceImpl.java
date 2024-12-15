package springcore.basic.order;

import springcore.basic.discount.DiscountPolicy;
import springcore.basic.discount.FixDiscountPolicy;
import springcore.basic.member.Member;
import springcore.basic.member.MemberRepository;
import springcore.basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 회원 저장소
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 할인 정책
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 주문 생성
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByID(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
