package com.haulmont.testtask.resource;

import com.haulmont.testtask.api.dto.ClientDTO;
import com.haulmont.testtask.api.resource.ClientResource;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки веб запросов к Client
 */
@RequiredArgsConstructor
@Controller
@Slf4j
public class ClientResourceImpl implements ClientResource {

    private final ClientService clientService;

    private final ConversionService conversionService;

    /**
     * Получить всех client
     *
     * @param model - объект Model для хранения данных
     * @return - заполненную страницу clientList
     */
    @Override
    public String getAll(Model model) {
        List<Client> clientList = clientService.getAllClient();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Client client : clientList) {
            clientDTOList.add(conversionService.convert(client, ClientDTO.class));
        }
        model.addAttribute("clients", clientDTOList);
        log.info("getAll() - Получены все client");
        return "clientList";
    }

    /**
     * Добавление нового Client
     *
     * @param model - объект Model для хранения данных
     * @return - страницу addClient
     */
    @Override
    public String create(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        log.debug("create() - Форма добавления client");
        return "addClient";
    }

    /**
     * Добавление нового Client
     *
     * @param clientDTO     - clientDTO для заполения формы
     * @param bindingResult - объект для валидации входных параметров
     * @return - вернет страницу addClient в случае неверных параметров и редирект на страницу clientList в случае успеха
     */
    @Override
    public String create(ClientDTO clientDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addClient";
        }
        Client clientConvert = conversionService.convert(clientDTO, Client.class);
        Client clientResult = clientService.create(clientConvert);
        ClientDTO clientDTOCheck = conversionService.convert(clientResult, ClientDTO.class);
        log.info("create() - Создан новый Client {}", clientDTOCheck);
        return "redirect:allClient";
    }

    /**
     * Обновление Client
     *
     * @param model - объект Model для хранения данных
     * @return - страницу updateClient
     */
    @Override
    public String update(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        List<Client> clientList = clientService.getAllClient();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Client client : clientList) {
            clientDTOList.add(conversionService.convert(client, ClientDTO.class));
        }
        model.addAttribute("clients", clientDTOList);
        log.debug("update() - Форма обновления client");
        return "updateClient";
    }

    /**
     * Обновление Client
     *
     * @param clientDTO     - clientDTO для заполения формы
     * @param bindingResult - объект для валидации входных параметров
     * @param model         - объект Model для хранения данных
     * @return - - вернет страницу updateClient в случае неверных параметров и редирект на страницу clientList в случае успеха
     */
    @Override
    public String update(ClientDTO clientDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Client> clientList = clientService.getAllClient();
            List<ClientDTO> clientDTOList = new ArrayList<>();
            for (Client client : clientList) {
                clientDTOList.add(conversionService.convert(client, ClientDTO.class));
            }
            model.addAttribute("clients", clientDTOList);
            return "updateClient";
        }
        Client clientConvert = conversionService.convert(clientDTO, Client.class);
        Client clientResult = clientService.create(clientConvert);
        ClientDTO clientDTOCheck = conversionService.convert(clientResult, ClientDTO.class);
        log.info("update() - Обновлен Client: {}", clientDTOCheck);
        return "redirect:allClient";
    }

    /**
     * Удаление Client
     *
     * @param model - объект Model для хранения данных
     * @return страница deleteClient для первоначального отображения
     */
    @Override
    public String delete(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        List<Client> clientList = clientService.getAllClient();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Client client : clientList) {
            clientDTOList.add(conversionService.convert(client, ClientDTO.class));
        }
        model.addAttribute("clients", clientDTOList);
        log.debug("delete() - Форма удаления client");
        return "deleteClient";
    }

    /**
     * Удаление Client
     *
     * @param clientDTO - clientDTO для заполения формы
     * @return - редирект на страницу со всеми client
     */
    @Override
    public String delete(ClientDTO clientDTO) {
        Client clientResult = clientService.deleteById(clientDTO.getClientId());
        ClientDTO clientDTOCheck = conversionService.convert(clientResult, ClientDTO.class);
        log.info("delete() - Удален client: {}", clientDTOCheck);
        return "redirect:allClient";
    }

}