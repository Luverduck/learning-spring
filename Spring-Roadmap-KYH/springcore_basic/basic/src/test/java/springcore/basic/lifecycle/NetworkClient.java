package springcore.basic.lifecycle;

public class NetworkClient {

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

    // 빈 초기화시 실행할 메소드 정의
    public void init() throws Exception {
        System.out.println("NetworkClient.init()");
        connect();
        call("초기화 연결 메시지");
    }

    // 빈 소멸시 실행할 메소드 정의
    public void close() throws Exception {
        System.out.println("NetworkClient.close()");
        disconnect();
    }

}
