package springmvc.itemservice.domain.item;

import lombok.*;

// @Data는 @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashcode를 모두 포함한다.
@Data
public class Item {

    // 필드
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    // 기본 생성자
    public Item() {

    }
    
    // 생성자
    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
