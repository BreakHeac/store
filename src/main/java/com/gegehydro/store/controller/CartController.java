package com.gegehydro.store.controller;

import com.gegehydro.store.entity.Cart;
import com.gegehydro.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 购物车控制类
 *
 * @author sunhao
 * Create on 2018/1/4
 */
@CrossOrigin
@PreAuthorize("hasAnyRole('USER','ADMIN')")
@RestController
@RequestMapping(value = "/cart", produces = "application/json;charset=UTF-8")
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = "/addCart")
    public String addCart(Cart cart, HttpServletRequest request) {
        return cartService.addCart(cart, request);
    }

    @PostMapping(value = "/deleteCart")
    public String deleteCart(Cart cart, HttpServletRequest request) {
        return cartService.deleteCart(cart, request);
    }

    @GetMapping(value = "/getCart")
    public String getCart(HttpServletRequest request) {
        return cartService.getCart(request);
    }

    @GetMapping(value = "/getCartNum")
    public String getCartNum(HttpServletRequest request) {
        return cartService.getCart(request);
    }
}
