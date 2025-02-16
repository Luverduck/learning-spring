package springcore.basic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest {

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return this.count;
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    @Scope("singleton")
    static class ClientBean {
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Test
    void singletonClientUsePrototype() {
        // 컨테이너 생성
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        // 컨테이너에서 싱글톤 스코프의 스프링 빈 반환
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        // 싱글톤 빈 안의 프로토타입 빈의 필드 count를 1만큼 증가
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        // 컨테이너에서 싱글톤 스코프의 스프링 빈 반환
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        // 싱글톤 빈 안의 프로토타입 빈의 필드 count를 1만큼 증가
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

}
