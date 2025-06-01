package com.example.bookstore.controllers;

import com.example.bookstore.models.User;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.OrderService;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String viewOrders(Model model, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        model.addAttribute("orders", orderService.getOrdersByUser(user));
        return "orders";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "checkout";
    }

    @PostMapping("/place")
    public String placeOrder(Authentication authentication, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findByEmail(authentication.getName());
            orderService.createOrder(user);
            Locale locale = LocaleContextHolder.getLocale();
            redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("message.order.success", null, locale));
            return "redirect:/orders";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cart";
        }
    }
}