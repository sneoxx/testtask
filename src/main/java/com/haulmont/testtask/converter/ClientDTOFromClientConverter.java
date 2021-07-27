package com.haulmont.testtask.converter;

import com.haulmont.testtask.api.dto.ClientDTO;
import com.haulmont.testtask.entity.Client;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация из Client в ClientDTO
 */
@Component
public class ClientDTOFromClientConverter implements Converter<Client, ClientDTO> {

    @Override
    public ClientDTO convert(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFullName(client.getFullName());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setClientId(client.getClientId());
        clientDTO.setPassportNumber(client.getPassportNumber());
        clientDTO.setEmail(client.getEmail());
        return clientDTO;
    }

}