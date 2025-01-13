package springcore.basic.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcore.basic.AutoAppConfig;
import springcore.basic.discount.DiscountPolicy;
import springcore.basic.member.Grade;
import springcore.basic.member.Member;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        // 스프링 컨테이너 생성 후 AutoAppConfig와 DiscountService를 컨테이너에 등록
        // 컴포넌트 스캔을 통해 빈 등록 후 DiscountService에 자동 의존 관계 주입
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        // 컨테이너에서 DiscountService 타입 빈 반환
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        // 고정 할인 정책 적용 시 할인 액수
        int fixDiscountPrice = discountService.discount(member, 20000, "fixDiscountPolicy");
        assertThat(fixDiscountPrice).isEqualTo(1000);
        // 비율 할인 정책 적용 시 할인 액수
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        // DiscountPolicy 타입 빈의 이름과 객체를 Map으로 반환
        private final Map<String, DiscountPolicy> policyMap;
        // DiscountPolicy 타입 빈 객체를 List로 반환
        private final List<DiscountPolicy> policies;
        // 생성자 주입
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }
        // discountCode에 따라 할인 정책을 적용했을 때 할인 액수를 반환
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }

}
