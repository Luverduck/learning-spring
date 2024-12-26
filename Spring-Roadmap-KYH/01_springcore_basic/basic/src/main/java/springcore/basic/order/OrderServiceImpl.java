package springcore.basic.order;

import springcore.basic.discount.DiscountPolicy;
import springcore.basic.member.Member;
import springcore.basic.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    // 회원 저장소
    private final MemberRepository memberRepository;

    // 할인 정책
    private final DiscountPolicy discountPolicy;

    // 생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 주문 생성
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByID(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // memberRepository를 반환하는 메소드
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }

}
