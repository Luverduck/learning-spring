package springcore.basic.order;

public class Order {

    // 속성
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    // 메소드
    public int calculatePrice() {
        return this.itemPrice - this.discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName = " + itemName + '\'' +
                ", itemPrice = " + itemPrice +
                ", discountPrice = " + discountPrice +
                "}";
    }

    // Getter/Setter
    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    // 생성자
    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

}
