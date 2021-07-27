package com.haulmont.testtask.api.resource;

import com.haulmont.testtask.api.dto.CreditAdvertiseDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Интерфейс для обработки веб запросов к CreditAdvertise
 */
@RequestMapping(value = {"/creditAdvertise"})
public interface CreditAdvertiseResource {

    @GetMapping("/allCreditAdvertise")
    String getAll(Model model);

    @GetMapping("/addCreditAdvertise")
    String addCreditAdvertise(Model model);

    @PostMapping("/addCreditAdvertise")
    String addCreditAdvertise(@Valid CreditAdvertiseDTO creditAdvertiseDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes);

    @GetMapping("/createCreditAdvertise")
    String createCreditAdvertise();

    @PostMapping("/createCreditAdvertise")
    String createCreditAdvertise(@ModelAttribute("creditAdvertiseDTO") CreditAdvertiseDTO creditAdvertiseDTO, BindingResult bindingResult);

    @GetMapping("/deleteCreditAdvertise")
    String delete(Model model);

    @PostMapping("/deleteCreditAdvertise")
    String delete(CreditAdvertiseDTO creditAdvertiseDTO);

}