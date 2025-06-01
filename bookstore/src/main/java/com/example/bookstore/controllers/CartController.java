package com.example.bookstore.controllers;

import com.example.bookstore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "cart";
    }

    @PostMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId, @RequestParam(defaultValue = "1") int quantity,
                            RedirectAttributes redirectAttributes) {
        try {
            cartService.addToCart(bookId, quantity);
            Locale locale = LocaleContextHolder.getLocale();
            redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("message.cart.add.success", null, locale));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/books";
    }

    @PostMapping("/update/{bookId}")
    public String updateQuantity(@PathVariable Long bookId, @RequestParam int quantity,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (quantity <= 0) {
                cartService.removeFromCart(bookId);
            } else {
                cartService.updateQuantity(bookId, quantity);
            }
            Locale locale = LocaleContextHolder.getLocale();
            redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("message.cart.update.success", null, locale));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId, RedirectAttributes redirectAttributes) {
        try {
            cartService.removeFromCart(bookId);
            Locale locale = LocaleContextHolder.getLocale();
            redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("message.cart.remove.success", null, locale));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(RedirectAttributes redirectAttributes) {
        cartService.clearCart();
        Locale locale = LocaleContextHolder.getLocale();
        redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("message.cart.clear.success", null, locale));
        return "redirect:/cart";
    }
}