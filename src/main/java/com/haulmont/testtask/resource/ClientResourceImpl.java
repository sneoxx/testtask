package com.haulmont.testtask.resource;

import com.haulmont.testtask.dto.ClientDTO;
import com.haulmont.testtask.entity.Client;
import com.haulmont.testtask.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ClientResourceImpl {

    private final ClientService clientService;

    private final ConversionService conversionService;

    /**
     * @param model - объект Model для хранения данных
     * @return - заполненную страницу clientList
     */
    @RequestMapping(value = {"/clientList"}, method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Client> clientList = clientService.getAllClient();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Client client : clientList) {
            clientDTOList.add(conversionService.convert(client, ClientDTO.class));
        }
        model.addAttribute("clients", clientDTOList);
        log.info("getAll()- Получены все client");
        return "clientList";
    }

    /**
     * @param model - объект Model для хранения данных
     * @return - страницу addClient
     */
    @RequestMapping(value = {"/addClient"}, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        return "addClient";
    }

    /**
     * @param clientDTO     - clientDTO для заполения формы
     * @param bindingResult - объект для валидации входных параметров
     * @return - вернет страницу addClient в случае неверных параметров и редирект на страницу clientList в случае успеха
     */
    @RequestMapping(value = {"/addClient"}, method = RequestMethod.POST)
    public String create(@Valid ClientDTO clientDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addClient";
        }
        Client clientConvert = conversionService.convert(clientDTO, Client.class);
        Client clientResult = clientService.create(clientConvert);
        ClientDTO clientDTOCheck = conversionService.convert(clientResult, ClientDTO.class);
        log.info("create() - Создан новый Client {}", clientDTOCheck);
        return "redirect:clientList";
    }

    /**
     * @param model - объект Model для хранения данных
     * @return - страницу updateClient
     */
    @RequestMapping(value = {"/updateClient"}, method = RequestMethod.GET)
    public String update(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        List<Client> clientList = clientService.getAllClient();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Client client : clientList) {
            clientDTOList.add(conversionService.convert(client, ClientDTO.class));
        }
        model.addAttribute("clients", clientDTOList);
        return "updateClient";
    }

    /**
     * @param clientDTO     - clientDTO для заполения формы
     * @param bindingResult - объект для валидации входных параметров
     * @param model         - объект Model для хранения данных
     * @return - - вернет страницу updateClient в случае неверных параметров и редирект на страницу clientList в случае успеха
     */
    @RequestMapping(value = {"/updateClient"}, method = RequestMethod.POST)
    public String update(@Valid ClientDTO clientDTO, BindingResult bindingResult, Model model) {
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
        return "redirect:clientList";
    }

    /**
     * @param model - объект Model для хранения данных
     * @return страница deleteClient для первоначального отображения
     */
    @RequestMapping(value = {"/deleteClient"}, method = RequestMethod.GET)
    public String delete(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        List<Client> clientList = clientService.getAllClient();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Client client : clientList) {
            clientDTOList.add(conversionService.convert(client, ClientDTO.class));
        }
        model.addAttribute("clients", clientDTOList);
        return "deleteClient";
    }

    /**
     * Удаление клиента по id
     *
     * @param clientDTO - clientDTO для заполения формы
     * @return - редирект на страницу со всеми client
     */
    @RequestMapping(value = {"/deleteClient"}, method = RequestMethod.POST)
    public String delete(ClientDTO clientDTO) {
        Client clientResult = clientService.deleteById(clientDTO.getClientId());
        ClientDTO clientDTO1 = conversionService.convert(clientResult, ClientDTO.class);
        log.info("delete() - Удален client: {}", clientDTO1);
        return "redirect:clientList";
    }
}