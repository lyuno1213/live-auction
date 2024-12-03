package io.bootify.live_auction.controller;

import io.bootify.live_auction.model.ReceiptDTO;
import io.bootify.live_auction.model.ReceiptStatus;
import io.bootify.live_auction.service.ReceiptService;
import io.bootify.live_auction.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(final ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("receiptStatusValues", ReceiptStatus.values());
    }

    @GetMapping
    public String list(@RequestParam(name = "filter", required = false) final String filter,
            @SortDefault(sort = "id") @PageableDefault(size = 20) final Pageable pageable,
            final Model model) {
        final Page<ReceiptDTO> receipts = receiptService.findAll(filter, pageable);
        model.addAttribute("receipts", receipts);
        model.addAttribute("filter", filter);
        model.addAttribute("paginationModel", WebUtils.getPaginationModel(receipts));
        return "receipt/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("receipt") final ReceiptDTO receiptDTO) {
        return "receipt/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("receipt") @Valid final ReceiptDTO receiptDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "receipt/add";
        }
        receiptService.create(receiptDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("receipt.create.success"));
        return "redirect:/receipts";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Integer id, final Model model) {
        model.addAttribute("receipt", receiptService.get(id));
        return "receipt/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Integer id,
            @ModelAttribute("receipt") @Valid final ReceiptDTO receiptDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "receipt/edit";
        }
        receiptService.update(id, receiptDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("receipt.update.success"));
        return "redirect:/receipts";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Integer id,
            final RedirectAttributes redirectAttributes) {
        receiptService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("receipt.delete.success"));
        return "redirect:/receipts";
    }

}
