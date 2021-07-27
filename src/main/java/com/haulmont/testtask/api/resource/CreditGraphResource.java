package com.haulmont.testtask.api.resource;

import com.haulmont.testtask.api.dto.CreditGraphDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Интерфейс для обработки веб запросов к Сredit Graph
 */
@RequestMapping(value = {"/creditGraph"})
public interface CreditGraphResource {

    @GetMapping("/allCreditGraph")
    String getAll(Model model);

    @PostMapping("/allCreditGraph")
    String getAll(@Valid CreditGraphDTO creditGraphDTO, Model model);

}