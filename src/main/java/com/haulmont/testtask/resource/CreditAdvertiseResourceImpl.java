package com.haulmont.testtask.resource;

import com.haulmont.testtask.api.dto.ClientDTO;
import com.haulmont.testtask.api.dto.CreditAdvertiseDTO;
import com.haulmont.testtask.api.dto.CreditDTO;
import com.haulmont.testtask.api.resource.CreditAdvertiseResource;
import com.haulmont.testtask.api.validator.CreditLimitValidator;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.entity.Credit;
import com.haulmont.testtask.entity.CreditAdvertise;
import com.haulmont.testtask.entity.CreditGraph;
import com.haulmont.testtask.exception.CreditLimitException;
import com.haulmont.testtask.service.ClientService;
import com.haulmont.testtask.service.CreditAdvertiseService;
import com.haulmont.testtask.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки веб запросов к CreditAdvertise
 */
@RequiredArgsConstructor
@Controller
@Slf4j
public class CreditAdvertiseResourceImpl implements CreditAdvertiseResource {

    private final CreditAdvertiseService creditAdvertiseService;

    private final ClientService clientService;

    private final CreditService creditService;

    private final ConversionService conversionService;

    private final CreditLimitValidator creditLimitValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(creditLimitValidator);
    }

    /**
     * Получить все creditAdvertise
     *
     * @param model - объект Model для хранения данных
     * @return - заполненную страницу creditAdvertiseList
     */
    @Override
    public String getAll(Model model) {
        model.addAttribute("creditAdvertises", getCreditAdvertiseDTOList());
        log.info("getAll() - Получены все CreditAdvertise");
        return "creditAdvertiseList";
    }

    /**
     * Создать новое creditAdvertise, ввод начальных значений, http метод get
     *
     * @param model - объект Model для хранения данных
     * @return - страницу addCreditAdvertise
     */
    @Override
    public String addCreditAdvertise(Model model) {
        model.addAttribute("creditAdvertiseDTO", new CreditAdvertiseDTO());
        model.addAttribute("clients", getClientDTOList());
        model.addAttribute("credits", getCreditDTOList());
        log.debug("addCredit() - Форма ввода начальных значений для добавления CreditAdvertise");
        return "addCreditAdvertise";
    }

    /**
     * Создать новое creditAdvertise, ввод начальных значенний, http метод post
     *
     * @param creditAdvertiseDTO - CreditAdvertiseDTO для заполения формы
     * @param bindingResult      - объект для валидации входных параметров
     * @return - страницу addCreditAdvertise в случае неверных параметров и редирект на страницу CreditAdvertiseList в случае успеха
     */
    @Override
    public String addCreditAdvertise(CreditAdvertiseDTO creditAdvertiseDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("creditAdvertiseDTO", creditAdvertiseDTO);
            model.addAttribute("clients", getClientDTOList());
            model.addAttribute("credits", getCreditDTOList());
            log.warn("create() - Ошибка ввода начальных значений CreditAdvertise");
            return "addCreditAdvertise";
        }
        if ((creditService.getCredit(creditAdvertiseDTO.getCreditId()).getCreditLimit().compareTo(creditAdvertiseDTO.getCreditAmount())) < 0) {
            log.warn("create() - Превышение введенного Credit Amount над Credit Limit");
            throw new CreditLimitException("Exceeded Credit Limit");
        }

        creditAdvertiseDTO.setCreditName(creditService.getCredit(creditAdvertiseDTO.getCreditId()).getCreditName());
        creditAdvertiseDTO.setClientName(clientService.getClient(creditAdvertiseDTO.getClientId()).getFullName());
        creditAdvertiseDTO.setInterestRate(creditService.getCredit(creditAdvertiseDTO.getCreditId()).getInterestRate());
        BigDecimal interestAmount = (creditAdvertiseDTO.getInterestRate().multiply(new BigDecimal(creditAdvertiseDTO.getCreditAmount()))
                .divide(new BigDecimal(100), 2)).divide(new BigDecimal(12), 2)
                .multiply((new BigDecimal(creditAdvertiseDTO.getLoanTermMonths())));
        BigDecimal fullCreditAmount = interestAmount.add(new BigDecimal(creditAdvertiseDTO.getCreditAmount()));
        Integer loanTermMonths = creditAdvertiseDTO.getLoanTermMonths();
        creditAdvertiseDTO.setInterestAmount(interestAmount);
        creditAdvertiseDTO.setFullCreditAmount(fullCreditAmount);
        creditAdvertiseDTO.setCreditGraphs(getCreditGraphList(interestAmount, fullCreditAmount, loanTermMonths, creditAdvertiseDTO));
        redirectAttributes.addFlashAttribute("creditAdvertiseDTO", creditAdvertiseDTO);
        log.info("create() - Введены начальные значения CreditAdvertise");
        return "redirect:createCreditAdvertise";
    }

    /**
     * Создать новое creditAdvertise, вывод графика клатежей и параметров кредита, http метод get
     *
     * @return - страницу creditAdvertise
     */
    @Override
    public String createCreditAdvertise() {
        log.debug("createCreditAdvertise() - Форма добавления CreditAdvertise, вывод графика клатежей и параметров кредита");
        return "createCreditAdvertise";
    }

    /**
     * Создать новое creditAdvertise, вывод графика клатежей и параметров кредита, http метод post
     *
     * @param creditAdvertiseDTO - CreditAdvertiseForm для заполения формы
     * @param bindingResult      - объект для валидации входных параметров
     * @return - вернет страницу updateCreditAdvertise в случае неверных параметров и редирект на страницу CreditAdvertiseList в случае успеха
     */
    @Override
    public String createCreditAdvertise(CreditAdvertiseDTO creditAdvertiseDTO, BindingResult bindingResult) {
        CreditAdvertise creditAdvertise = conversionService.convert(creditAdvertiseDTO, CreditAdvertise.class);
        CreditAdvertise creditAdvertiseResult = creditAdvertiseService.create(creditAdvertise);
        CreditAdvertiseDTO CreditAdvertiseDTOCheck = conversionService.convert(creditAdvertiseResult, CreditAdvertiseDTO.class);
        log.info("createCreditAdvertise() - Создан новый CreditAdvertise {}", CreditAdvertiseDTOCheck);
        return "redirect:allCreditAdvertise";
    }

    /**
     * Удаление creditAdvertise, http метод get
     *
     * @param model - объект Model для хранения данных
     * @return - страница deleteCreditAdvertise для первоначального отображения
     */
    @Override
    public String delete(Model model) {
        model.addAttribute("creditAdvertiseDTO", new CreditAdvertiseDTO());
        model.addAttribute("creditAdvertises", getCreditAdvertiseDTOList());
        log.debug("delete() - Форма удаления creditAdvertise");
        return "deleteCreditAdvertise";
    }

    /**
     * Удаление creditAdvertise, http метод post
     *
     * @param creditAdvertiseDTO - CreditAdvertiseDTO для заполения формы
     * @return - редирект на страницу со всеми CreditAdvertise
     */
    @Override
    public String delete(CreditAdvertiseDTO creditAdvertiseDTO) {
        CreditAdvertise creditAdvertiseResult = creditAdvertiseService.deleteById(creditAdvertiseDTO.getCreditAdvertiseId());
        CreditAdvertiseDTO creditAdvertiseDTOCheck = conversionService.convert(creditAdvertiseResult, CreditAdvertiseDTO.class);
        log.info("delete() - Удален CreditAdvertise: {}", creditAdvertiseDTOCheck);
        return "redirect:allCreditAdvertise";
    }

    /**
     * Получить всех Client и сконвертировать в ClientDTO
     *
     * @return - clientDTOList
     */
    public List<ClientDTO> getClientDTOList() {
        List<Client> clientList = clientService.getAllClient();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Client client : clientList) {
            clientDTOList.add(conversionService.convert(client, ClientDTO.class));
        }
        log.debug("getClientDTOList() - ClientDTOList получен {}", clientDTOList);
        return clientDTOList;
    }

    /**
     * Получить всех Credit и сконвертировать в CreditDTO
     *
     * @return - creditDTOList
     */
    public List<CreditDTO> getCreditDTOList() {
        List<Credit> creditList = creditService.getAllCredit();
        List<CreditDTO> creditDTOList = new ArrayList<>();
        for (Credit credit : creditList) {
            creditDTOList.add(conversionService.convert(credit, CreditDTO.class));
        }
        log.debug("getCreditDTOList() - Все Credit получены  и сконвертированы {}", creditDTOList);
        return creditDTOList;
    }

    /**
     * Получить все CreditAdvertise и сконвертировать в CreditAdvertiseDTO
     *
     * @return - creditAdvertiseDTOList
     */
    public List<CreditAdvertiseDTO> getCreditAdvertiseDTOList() {
        List<CreditAdvertise> creditAdvertiseList = creditAdvertiseService.getAllCreditAdvertise();
        List<CreditAdvertiseDTO> creditAdvertiseDTOList = new ArrayList<>();
        for (CreditAdvertise creditAdvertise : creditAdvertiseList) {
            creditAdvertiseDTOList.add(conversionService.convert(creditAdvertise, CreditAdvertiseDTO.class));
        }
        log.debug("getCreditAdvertiseDTOList() - Все CreditAdvertise получены и сконвертированы {}", creditAdvertiseDTOList);
        return creditAdvertiseDTOList;
    }

    /**
     * Получить заполненый CreditGraph
     *
     * @param interestAmount     - сумма процентов
     * @param fullCreditAmount   -  полная сумма кредита
     * @param loanTermMonths     - срок кредита в месяцах
     * @param creditAdvertiseDTO - creditAdvertiseDTO
     * @return - CreditGraph
     */
    public List<CreditGraph> getCreditGraphList(BigDecimal interestAmount, BigDecimal fullCreditAmount, Integer loanTermMonths, CreditAdvertiseDTO creditAdvertiseDTO) {
        List<CreditGraph> creditGraphList = new ArrayList<>();
        int paymentDate = 0;
        for (int i = 0; i < loanTermMonths; i++) {
            CreditGraph creditGraph = new CreditGraph();
            creditGraph.setAmountPayment(fullCreditAmount.divide(new BigDecimal(loanTermMonths), 2));
            creditGraph.setBodyPayment(new BigDecimal(creditAdvertiseDTO.getCreditAmount() * loanTermMonths));
            creditGraph.setInterestPayment(interestAmount.divide(new BigDecimal(loanTermMonths), 2));
            creditGraph.setPaymentDate(LocalDate.now().plusMonths(paymentDate));
            paymentDate++;
            creditGraphList.add(creditGraph);
        }
        log.debug("getCreditGraphList() - CreditGraphList получен {}", creditGraphList);
        return creditGraphList;
    }

}