package com.haulmont.testtask.api.resource;

import com.haulmont.testtask.api.dto.ClientDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Интерфейс для обработки веб запросов к Client
 */
@RequestMapping(value = {"/client"})
public interface ClientResource {

    @GetMapping("/allClient")
    String getAll(Model model);

    @GetMapping("/addClient")
    String create(Model model);

    @PostMapping("/addClient")
    String create(@Valid ClientDTO clientDTO, BindingResult bindingResult);

    @GetMapping("/updateClient")
    String update(Model model);

    @PostMapping("/updateClient")
    String update(ClientDTO clientDTO, RedirectAttributes redirectAttributes);

    @GetMapping("/updateSelectedClient")
    String updateSelectedClient();

    @PostMapping("/updateSelectedClient")
    String updateSelectedClient(@Valid @ModelAttribute("clientDTO") ClientDTO clientDTO, BindingResult bindingResult, Model model);

    @GetMapping("/deleteClient")
    String delete(Model model);

    @PostMapping("/deleteClient")
    String delete(ClientDTO clientDTO);

}