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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
        List<CreditDTO> creditDTOList = getAllCreditDTO();
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
     * @param creditDTO   - CreditDTO для заполения формы
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
     * Обновление Credit, http метод get
     *
     * @param model - объект Model для хранения данных
     * @return - страницу updateCredit
     */
    @Override
    public String update(Model model) {
        model.addAttribute("creditDTO", new CreditDTO());
        List<CreditDTO> creditDTOList = getAllCreditDTO();
        model.addAttribute("credits", creditDTOList);
        log.debug("update() - Форма обновления credit");
        return "updateCredit";
    }

    /**
     * Обновление выбранного Credit, http метод post
     *
     * @param creditDTO - creditDTO для заполнения формы
     * @return - редирект на страницу updateSelectedClient
     */
    @Override
    public String update(CreditDTO creditDTO, RedirectAttributes redirectAttributes) {
        Credit credit = creditService.getCredit(creditDTO.getCreditId());
        CreditDTO creditDTOResult = conversionService.convert(credit, CreditDTO.class);
        redirectAttributes.addFlashAttribute("creditDTO", creditDTOResult);
        log.info("update() - Выбран creditDTO для обновления");
        return "redirect:updateSelectedCredit";
    }

    /**
     * Форма обновления выбранного Credit, http метод get
     *
     * @return - страницу updateSelectedCredit
     */
    @Override
    public String updateSelectedCredit() {
        log.debug("updateSelectedCredit() - Форма обновления выбранного кредита");
        return "updateSelectedCredit";
    }

    /**
     *
     * Обновление выбранного Credit, http метод post
     *
     * @param creditDTO     - creditDTO для заполения формы
     * @param bindingResult - объект для валидации входных параметров
     * @param model         - объект Model для хранения данных
     * @return     - вернет страницу updateSelectedCredit в случае неверных параметров и редирект на страницу allCredit в случае успеха
     */
    @Override
    public String updateSelectedCredit(@Valid @ModelAttribute("creditDTO") CreditDTO creditDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("updateSelectedCredit() - Введены неверные значения Credit: {}", creditDTO);
            return "updateSelectedCredit";
        }
        Credit creditConvert = conversionService.convert(creditDTO, Credit.class);
        Credit creditResult = creditService.create(creditConvert);
        CreditDTO creditDTOCheck = conversionService.convert(creditResult, CreditDTO.class);
        log.info("updateSelectedCredit() - Обновлен Credit: {}", creditDTOCheck);
        return "redirect:allCredit";
    }


    /**
     * Удаление Credit по id, http метод get
     *
     * @param model - объект Model для хранения данных
     * @return страница deleteCredit для первоначального отображения
     */
    @Override
    public String delete(Model model) {
        model.addAttribute("creditDTO", new CreditDTO());
        List<CreditDTO> creditDTOList = getAllCreditDTO();
        model.addAttribute("credits", creditDTOList);
        log.debug("delete() - Форма удаления credit");
        return "deleteCredit";
    }

    /**
     * Удаление Credit по id, http метод post
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

    /**
     * Получение всех Credit из БД и конвертация их в CreditDTO
     * @return - List всех CreditDTO из БД
     */
    public List<CreditDTO> getAllCreditDTO() {
        List<Credit> creditList = creditService.getAllCredit();
        List<CreditDTO> creditDTOList = new ArrayList<>();
        for (Credit credit : creditList) {
            creditDTOList.add(conversionService.convert(credit, CreditDTO.class));
        }
        log.debug("getAllCreditDTO() - все Credit из БД успешно получены и сконвертированы в CreditDTO: {}", creditDTOList);
        return creditDTOList;
    }

}