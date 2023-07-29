package shop.study.dto.itemDto;

import lombok.Getter;
import lombok.Setter;
import shop.study.constant.ItemSellStatus;

@Getter@Setter
public class ItemSearchDto {
    private String searchDateType;

    private ItemSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery ="";
}
