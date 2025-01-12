package springcore.basic.discount;

import springcore.basic.member.Member;

public interface DiscountPolicy {

    // 할인 여부 판정
    int discount(Member member, int price);

}
