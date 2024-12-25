package springcore.basic.singleton;

public class StatefulService {

    // 필드
    private int price;

    // 메소드
    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

}
