package springcore.basic.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springcore.basic.member.Grade;
import springcore.basic.member.Member;

@Primary // DiscountPolicy 타입 기본 빈으로 지정
@Component
public class RateDiscountPolicy implements DiscountPolicy {

    // 할인율
    private int discountPercent = 10;

    // 할인 여부 판정
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return price * discountPercent / 100;
        return 0;
    }

}
