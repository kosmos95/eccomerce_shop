package shop.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.study.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
