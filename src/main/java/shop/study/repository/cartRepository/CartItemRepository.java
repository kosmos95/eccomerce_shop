package shop.study.repository.cartRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.study.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);
}
