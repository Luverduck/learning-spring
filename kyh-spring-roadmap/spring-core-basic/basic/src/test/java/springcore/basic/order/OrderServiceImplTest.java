package springcore.basic.order;

import org.junit.jupiter.api.Test;
import springcore.basic.discount.FixDiscountPolicy;
import springcore.basic.member.Grade;
import springcore.basic.member.Member;
import springcore.basic.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        // MemoryMemberRepository의 인스턴스 memoryMemberRepository 생성
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        // memoryMemberRepository의 save 메소드 호출
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));

        // OrderServiceImpl의 인스턴스 orderServiceImpl 생성
        // - 생성자 호출에 필요한 인수를 new로 직접 생성하여 테스트 가능
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
        // orderServiceImpl의 createOrder 메소드 호출
        Order order = orderServiceImpl.createOrder(1L, "itemA", 10000);

        // 테스트 성공 조건은 createOrder 메소드의 호출 결과가 1000인 경우이다.
        // - Member의 Grade가 VIP이므로 할인가격은 1000이다.
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
