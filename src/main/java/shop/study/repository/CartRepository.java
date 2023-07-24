package shop.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.study.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
