package springcore.basic.discount;

import springcore.basic.member.Grade;
import springcore.basic.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    // 할인 금액 (고정 금액)
    private int discountFixAmount = 1000;

    // 할인 여부 판정
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return discountFixAmount;
        return 0;
    }

}
