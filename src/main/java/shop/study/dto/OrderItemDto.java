package shop.study.dto;

import lombok.Getter;
import lombok.Setter;
import shop.study.entity.OrderItem;

@Getter@Setter
public class OrderItemDto { //309p

    public OrderItemDto(OrderItem orderItem, String imgUrl){
        this.itemNm = orderItem.getItem().getItemNm();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }

    private String itemNm; //상품명

    private int count; //주문수량

    private int orderPrice; //주문 금액

    private String imgUrl;  //상품 이미지 경로
}
