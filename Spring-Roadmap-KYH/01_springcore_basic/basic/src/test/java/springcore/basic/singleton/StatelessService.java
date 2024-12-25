package springcore.basic.singleton;

public class StatelessService {

    // 필드
    // private int price; // 상태를 유지하지 않는다.

    // 메소드
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

    /*
    public int getPrice() {
        return this.price;
    }
    */

}
