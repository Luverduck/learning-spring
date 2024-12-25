package springcore.basic.singleton;

public class SingletonService {

    // 자신의 인스턴스를 클래스 변수로 포함하며 클래스 로딩 시점에 초기화 및 변경되지 않도록 한다.
    private static final SingletonService instance = new SingletonService();

    // 외부에서 인스턴스에 접근하기 위해서는 지정한 메소드를 통해 간접적으로 접근하도록 한다.
    // - static으로 선언한 것은 인스턴스 생성 없이 해당 메소드에 접근하도록 하기 위함이다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private로 설정하여 외부에서 생성자를 호출할 수 없도록 한다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
