package hello.itemservice.web.form;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class FormItemController {

    private final ItemRepository itemRepository;

    // 상품 목록
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "form/items";
    }

    // 상품 상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "form/item";
    }

    // 상품 등록 폼
    @GetMapping("/add")
    public String addForm(Model model) {
        // Model에 HTML 폼에 바인딩 할 객체 추가
        model.addAttribute("item", new Item());
        // 뷰의 이름 반환
        return "form/addForm";
    }

    // 상품 등록
    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        // HTML 폼 데이터의 배송 상태 값 확인
        log.info("item.open={}", item.getOpen());
        // 상품 등록
        Item savedItem = itemRepository.save(item);
        // Model에 상품 정보 추가
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        // 뷰의 이름 반환
        return "redirect:/form/items/{itemId}";
    }

    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        // Model에 HTML 폼에 바인딩 할 객체 추가
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        // 뷰의 이름 반환
        return "form/editForm";
    }

    // 상품 수정
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        // 상품 수정
        itemRepository.update(itemId, item);
        // 뷰의 이름 반환
        return "redirect:/form/items/{itemId}";
    }
}