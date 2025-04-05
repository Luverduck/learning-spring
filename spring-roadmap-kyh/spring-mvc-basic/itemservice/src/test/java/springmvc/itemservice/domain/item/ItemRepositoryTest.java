package springmvc.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // 테스트 준비
        // - 테스트 객체 생성
        Item item = new Item("itemA", 100, 10);
        // 상품 등록 기능 테스트
        Item savedItem = itemRepository.save(item);
        // 상품 상세 기능 테스트
        Item findItem = itemRepository.findById(item.getId());
        // 기능 검증
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // 테스트 준비
        // - 테스트 객체 생성
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 10000, 10);
        // - 상품 등록
        itemRepository.save(item1);
        itemRepository.save(item2);
        // 상품 목록 기능 테스트
        List<Item> result = itemRepository.findAll();
        // 기능 검증
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        // 테스트 준비
        // - 테스트 객체 생성
        Item item1 = new Item("item1", 10000, 10);
        // - 상품 등록
        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();
        // - 테스트 케이스 준비
        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(itemId, updateParam);
        // 상품 수정 기능 테스트
        Item findItem = itemRepository.findById(itemId);
        // 기능 검증
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
