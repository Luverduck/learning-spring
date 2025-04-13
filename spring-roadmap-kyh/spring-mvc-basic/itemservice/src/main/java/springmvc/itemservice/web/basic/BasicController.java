package springmvc.itemservice.web.basic;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.itemservice.domain.item.Item;
import springmvc.itemservice.domain.item.ItemRepository;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // final이나 @NonNull이 적용된 필드만 포함된 생성자를 자동으로 생성
public class BasicController {

    // 상품 저장소
    private final ItemRepository itemRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

    /**
     * 상품 목록
     * @param model
     * @return
     */
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     * 상품 상세
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * 상품 등록 폼
     * @return
     */
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /**
     * 상품 등록 - @RequestParam
     * @param itemName
     * @param price
     * @param quantity
     * @param model
     * @return
     */
//    @PostMapping("/add")
    public String addItemV1(@RequestParam("itemName") String itemName,
                            @RequestParam("price") int price,
                            @RequestParam("quantity") Integer quantity,
                            Model model) {
        // 상품 객체 생성 후 필드를 요청 매개변수의 값으로 초기화
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        // 상품 등록 처리
        itemRepository.save(item);
        // 등록한 상품의 정보를 Model을 통해 View로 반환
        model.addAttribute("item", item);
        // 상품 상세 View의 이름 반환
        return "basic/item";
    }

    /**
     * 상품 등록 - @ModelAttribute
     * @param item
     * @return
     */
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        // 상품 등록 처리
        itemRepository.save(item);
        // @ModelAttribute가 적용된 매개변수는 핸들러 실행 완료 전에 Model에 자동으로 추가된다.
        // Model에 추가할 때 @ModelAttribute의 name을 객체의 이름으로 사용한다.
        // model.addAttribute("item", item);
        // 상품 상세 View의 이름 반환
        return "basic/item";
    }

    /**
     * 상품 등록 - @ModelAttribute의 name 생략
     * @param item
     * @return
     */
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        // @ModelAttribute의 name을 명시하지 않을 경우 매개변수 타입의 첫 글자를 소문자로 바꾼 이름을 모델명으로 사용한다. (Item -> item)
        // 상품 등록 처리
        itemRepository.save(item);
        // 상품 상세 View의 이름 반환
        return "basic/item";
    }

    /**
     * 상품 등록 - @ModelAttribute의 생략
     * @param item
     * @return
     */
//    @PostMapping("/add")
    public String addItemV4(Item item) {
        // 매개변수가 POJO 타입인 경우 @ModelAttribute를 생략할 수 있다.
        // 상품 등록 처리
        itemRepository.save(item);
        // 상품 상세 View의 이름 반환
        return "basic/item";
    }

    /**
     * 상품 등록 - PGR 방식 적용
     * @param item
     * @return
     */
    @PostMapping("/add")
    public String addItemV5(Item item) {
        // 상품 등록 처리
        itemRepository.save(item);
        // 리다이렉트 주소를 반환 (상품 상세)
        return "redirect:/basic/items/" + item.getId();
    }

    /**
     * 상품 수정 폼
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    /**
     * 상품 수정
     * @param itemId
     * @param item
     * @return
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) {
        // 상품 수정 처리
        itemRepository.update(itemId, item);
        // 리다이렉트 주소를 반환
        return "redirect:/basic/items/{itemId}";
    }
}
