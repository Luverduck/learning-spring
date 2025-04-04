package springmvc.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // 상품 저장소
    private static final Map<Long, Item> store = new HashMap<>();
    // 상품 ID 시퀀스
    private static long sequence = 0L;

    // 상품 저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // 상품 상세
    public Item findById(Long id) {
        return store.get(id);
    }

    // 상품 목록
    public List<Item> finddAll() {
        return new ArrayList<>(store.values());
    }

    // 상품 수정
    public void update(Long id, Item updateParam) {
        Item findItem = findById(id);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    // 상품 저장소 비우기(초기화)
    public void clearStore() {
        store.clear();
    }
}
