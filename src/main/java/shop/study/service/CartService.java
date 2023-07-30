package shop.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.study.dto.cartDto.CartDetailDto;
import shop.study.dto.cartDto.CartItemDto;
import shop.study.entity.Cart;
import shop.study.entity.CartItem;
import shop.study.entity.Item;
import shop.study.entity.Member;
import shop.study.repository.ItemRepository;
import shop.study.repository.MemberRepository;
import shop.study.repository.cartRepository.CartItemRepository;
import shop.study.repository.cartRepository.CartRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Long addCart(CartItemDto cartItemDto, String email) {
        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        Cart cart = cartRepository.findByMemberId(member.getId());

        if (cart == null) {
            /*
            * 상품을 처음으로 담을 경우  해당 회원의 장바구니 엔티티를 생성함
            * */

            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if (savedCartItem != null) {
            //장바구니에 이미 있던 상품은 기존 수량에 현재 장바구니에 담을 수량 만큼 더 해준다.
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        }else {
            //새로 담는다.
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email) {
        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());

        if (cart == null) {
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());

        return cartDetailDtoList;

    }
}
