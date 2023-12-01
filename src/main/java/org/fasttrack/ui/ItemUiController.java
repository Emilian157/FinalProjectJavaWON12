package org.fasttrack.ui;

import org.fasttrack.model.Item;
import org.fasttrack.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemUiController {

    private final ItemService itemService;

    @Autowired
    public ItemUiController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/list-items")
    public String itemList(Model model) {
        model.addAttribute("items", itemService.listAll());
        return "list-items";
    }

    @GetMapping("/list-items/{id}/edit")
    public String editItemForm(@PathVariable Long id, Model model) {
        Item item = itemService.getItem(id);
        model.addAttribute("item", item);
        return "edit-item";
    }

    @PostMapping("/list-items/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        itemService.addItem(item);
        redirectAttributes.addFlashAttribute("success", "Item added successfully!");
        return "redirect:/list-items";
    }

    @PostMapping("/list-items/{id}/edit")
    public String updateItem(@PathVariable Long id, @ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        itemService.updateItem(id, item);
        redirectAttributes.addFlashAttribute("success", "Item updated successfully!");
        return "redirect:/list-items";
    }

    @GetMapping("/list-items/{id}/delete")
    public String deleteItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        itemService.deleteItem(id);
        redirectAttributes.addFlashAttribute("success", "Item deleted successfully!");
        return "redirect:/list-items";
    }
}