package com.haulmont.testtask.resource;

import com.haulmont.testtask.api.resource.RootResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

/**
 * Класс для обработки веб запросов к root директории
 */
@RequiredArgsConstructor
@Controller
@Slf4j
public class RootResourceImpl implements RootResource {

    /**
     * Форма root директории
     *
     * @return - с корневая страница приложения
     */
    @Override
    public String getRoot() {
        log.info("root() - корневая страница");
        return "menu";
    }

}