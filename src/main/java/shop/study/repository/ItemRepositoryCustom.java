package shop.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.study.dto.itemDto.ItemSearchDto;
import shop.study.dto.MainItemDto;
import shop.study.entity.Item;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable); //282p
}
