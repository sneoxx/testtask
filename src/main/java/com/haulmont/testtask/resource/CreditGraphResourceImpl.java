package com.haulmont.testtask.resource;


import com.haulmont.testtask.api.dto.CreditGraphDTO;
import com.haulmont.testtask.api.resource.CreditGraphResource;
import com.haulmont.testtask.entity.CreditGraph;
import com.haulmont.testtask.service.CreditGraphService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.*;

/**
 * Класс для обработки веб запросов к CreditGraph
 */
@RequiredArgsConstructor
@Controller
@Slf4j
public class CreditGraphResourceImpl implements CreditGraphResource {

    private final CreditGraphService creditGraphService;

    private final ConversionService conversionService;

    /**
     * Получить все creditGraph
     *
     * @param model - объект Model для хранения данных
     * @return - заполненную страницу CreditGraphList
     */
    @Override
    public String getAll(Model model) {
        model.addAttribute("creditGraphDTO", new CreditGraphDTO());
        model.addAttribute("creditGraphs", getCreditGraphDTOSet());
        log.debug("getAll() - Форма выбора CreditAdvertise для получения CreditGraph");
        return "creditGraphList";
    }

    /**
     * Получить все creditGraph
     *
     * @param creditGraphDTO - заполненную в форме
     * @param model          - объект Model для хранения данных
     * @return - заполненную страницу creditGraphList
     */
    @Override
    public String getAll(@Valid CreditGraphDTO creditGraphDTO, Model model) {
        List<CreditGraphDTO> creditGraphFoundDTO = getCreditGraphFoundDTOList(creditGraphDTO);
        model.addAttribute("creditGraphs", getCreditGraphDTOSet());
        model.addAttribute("creditGraphss", creditGraphFoundDTO);
        log.info("getAll() - Получены все CreditGraph для выбранного СreditAdvertise: {}", creditGraphFoundDTO);
        return "creditGraphList";
    }

    /**
     * Получить все CreditGraph и сконвертировать в creditGraphDTOSet для удаления повторяющихся значений
     *
     * @return creditGraphDTOSet
     */
    public Set<UUID> getCreditGraphDTOSet() {
        Set<UUID> creditGraphDTOSet = new HashSet<>();
        List<CreditGraph> creditGraphList = creditGraphService.getAllCreditGraph();
        List<CreditGraphDTO> creditGraphDTOList = new ArrayList<>();
        for (CreditGraph creditGraph : creditGraphList) {
            creditGraphDTOList.add(conversionService.convert(creditGraph, CreditGraphDTO.class));
        }
        for (CreditGraphDTO creditGraphDTO : creditGraphDTOList) {
            creditGraphDTOSet.add(creditGraphDTO.getCreditAdvertise());
        }
        log.debug("getCreditGraphDTOSet() - creditGraphDTOSet из успешно получен: {}", creditGraphDTOSet);
        return creditGraphDTOSet;
    }

    /**
     * Получить график платежей для выбранного CreditAdvertise
     *
     * @param creditGraphDTO - выбранный в веб форме
     * @return - отсортированный CreditGraphDTO
     */
    public List<CreditGraphDTO> getCreditGraphFoundDTOList(CreditGraphDTO creditGraphDTO) {
        List<CreditGraph> creditGraphList = creditGraphService.getAllCreditGraph();
        List<CreditGraphDTO> creditGraphDTOList = new ArrayList<>();
        for (CreditGraph creditGraph : creditGraphList) {
            creditGraphDTOList.add(conversionService.convert(creditGraph, CreditGraphDTO.class));
        }
        UUID creditAdvertise = creditGraphDTO.getCreditAdvertise();
        List<CreditGraphDTO> creditGraphFoundDTOList = new ArrayList<>();
        for (CreditGraphDTO creditGraphFoundDTO : creditGraphDTOList) {
            if (creditGraphFoundDTO.getCreditAdvertise().equals(creditAdvertise)) {
                creditGraphFoundDTOList.add(creditGraphFoundDTO);
            }
        }
        log.debug("getCreditGraphFoundDTOList() - все CreditGraphDTO из БД успешно получены: {}", creditGraphDTOList);
        return creditGraphFoundDTOList;
    }
}