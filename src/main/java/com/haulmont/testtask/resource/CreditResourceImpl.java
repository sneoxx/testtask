package com.haulmont.testtask.resource;

import com.haulmont.testtask.api.dto.CreditDTO;
import com.haulmont.testtask.api.resource.CreditResource;
import com.haulmont.testtask.entity.Credit;
import com.haulmont.testtask.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки веб запросов к Credit
 */
@RequiredArgsConstructor
@Controller
@Slf4j
public class CreditResourceImpl implements CreditResource {

    private final CreditService creditService;

    private final ConversionService conversionService;

    /**
     * Получить все Credit
     *
     * @param model - объект Model для хранения данных
     * @return - заполненную страницу CreditList
     */
    @Override
    public String getAll(Model model) {
        List<Credit> creditList = creditService.getAllCredit();
        List<CreditDTO> creditDTOList = new ArrayList<>();
        for (Credit credit : creditList) {
            creditDTOList.add(conversionService.convert(credit, CreditDTO.class));
        }
        model.addAttribute("credits", creditDTOList);
        log.info("getAll() - Получены все credit");
        return "creditList";
    }

    /**
     * Добавление Credit по id
     *
     * @param model - объект Model для хранения данных
     * @return - страницу addCredit
     */
    @Override
    public String create(Model model) {
        model.addAttribute("creditDTO", new CreditDTO());
        log.debug("create() - Форма добвления credit");
        return "addCredit";
    }

    /**
     * Добавление Credit по id
     *
     * @param creditDTO     - CreditDTO для заполения формы
     * @param bindingResult - объект для валидации входных параметров
     * @return - вернет страницу addCredit в случае неверных параметров и редирект на страницу CreditList в случае успеха
     */
    @Override
    public String create(CreditDTO creditDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addCredit";
        }
        Credit creditConvert = conversionService.convert(creditDTO, Credit.class);
        Credit creditResult = creditService.create(creditConvert);
        CreditDTO creditDTOCheck = conversionService.convert(creditResult, CreditDTO.class);
        log.info("create() - Создан новый Credit {}", creditDTOCheck);
        return "redirect:allCredit";
    }

    /**
     * Обновление Credit по id
     *
     * @param model - объект Model для хранения данных
     * @return - страницу updateCredit
     */
    @Override
    public String update(Model model) {
        model.addAttribute("creditDTO", new CreditDTO());
        List<Credit> creditList = creditService.getAllCredit();
        List<CreditDTO> creditDTOList = new ArrayList<>();
        for (Credit credit : creditList) {
            creditDTOList.add(conversionService.convert(credit, CreditDTO.class));
        }
        model.addAttribute("credits", creditDTOList);
        log.debug("update() - Форма обновления credit");
        return "updateCredit";
    }

    /**
     * Обновление Credit по id
     *
     * @param creditDTO     - CreditDTO для заполения формы
     * @param bindingResult - объект для валидации входных параметров
     * @param model         - объект Model для хранения данных
     * @return - - вернет страницу updateCredit в случае неверных параметров и редирект на страницу CreditList в случае успеха
     */
    @Override
    public String update(CreditDTO creditDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Credit> creditList = creditService.getAllCredit();
            List<CreditDTO> creditDTOList = new ArrayList<>();
            for (Credit credit : creditList) {
                creditDTOList.add(conversionService.convert(credit, CreditDTO.class));
            }
            model.addAttribute("credits", creditDTOList);
            return "updateCredit";
        }
        Credit creditConvert = conversionService.convert(creditDTO, Credit.class);
        Credit creditResult = creditService.create(creditConvert);
        CreditDTO creditDTOCheck = conversionService.convert(creditResult, CreditDTO.class);
        log.info("update() - Обновлен Credit: {}", creditDTOCheck);
        return "redirect:allCredit";
    }

    /**
     * Удаление Credit по id
     *
     * @param model - объект Model для хранения данных
     * @return страница deleteCredit для первоначального отображения
     */
    @Override
    public String delete(Model model) {
        model.addAttribute("creditDTO", new CreditDTO());
        List<Credit> creditList = creditService.getAllCredit();
        List<CreditDTO> creditDTOList = new ArrayList<>();
        for (Credit credit : creditList) {
            creditDTOList.add(conversionService.convert(credit, CreditDTO.class));
        }
        model.addAttribute("credits", creditDTOList);
        log.debug("delete() - Форма удаления credit");
        return "deleteCredit";
    }

    /**
     * Удаление Credit по id
     *
     * @param creditDTO - CreditDTO для заполения формы
     * @return - редирект на страницу со всеми Credit
     */
    @Override
    public String delete(CreditDTO creditDTO) {
        Credit creditResult = creditService.deleteById(creditDTO.getCreditId());
        CreditDTO creditDTO1 = conversionService.convert(creditResult, CreditDTO.class);
        log.info("delete() - Удален credit: {}", creditDTO1);
        return "redirect:allCredit";
    }

}