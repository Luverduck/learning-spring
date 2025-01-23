package springcore.basic.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    // 서비스를 시작할 때 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + ", message : " + message);
    }

    // 서비스를 종료할 때 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    // InitializingBean의 afterPropertiesSet() 메소드 구현
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    // DisposableBean의 destroy() 메소드 구현
    @Override
    public void destroy() throws Exception {
        disconnect();
    }

}
