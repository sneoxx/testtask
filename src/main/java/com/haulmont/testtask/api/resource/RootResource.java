package com.haulmont.testtask.api.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Интерфейс для обработки веб запросов к Root
 */
@RequestMapping(value = {"/"})
public interface RootResource {

    @GetMapping()
    String getRoot();

}