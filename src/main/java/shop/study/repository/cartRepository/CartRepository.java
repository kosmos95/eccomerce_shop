package shop.study.repository.cartRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.study.entity.Cart;



public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberId(Long memberId);
}
