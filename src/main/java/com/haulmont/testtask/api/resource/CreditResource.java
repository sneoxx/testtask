package com.haulmont.testtask.api.resource;


import com.haulmont.testtask.api.dto.CreditDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Интерфейс для обработки веб запросов к Credit
 */

@RequestMapping(value = {"/credit"})
public interface CreditResource {

    @GetMapping("/allCredit")
    String getAll(Model model);

    @GetMapping("/addCredit")
    String create(Model model);

    @PostMapping("/addCredit")
    String create(@Valid CreditDTO creditDTO, BindingResult bindingResult);

    @GetMapping("/updateCredit")
    String update(Model model);

    @PostMapping("/updateCredit")
    String update(CreditDTO CreditDTO, RedirectAttributes redirectAttributes);

    @GetMapping("/updateSelectedCredit")
    String updateSelectedCredit();

    @PostMapping("/updateSelectedCredit")
    String updateSelectedCredit(@Valid @ModelAttribute("creditDTO") CreditDTO creditDTO, BindingResult bindingResult, Model model);

    @GetMapping("/deleteCredit")
    String delete(Model model);

    @PostMapping("/deleteCredit")
    String delete(CreditDTO creditDTO);

}