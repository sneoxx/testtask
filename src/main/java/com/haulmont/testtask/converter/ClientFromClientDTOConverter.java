package com.haulmont.testtask.converter;

import com.haulmont.testtask.dto.ClientDTO;
import com.haulmont.testtask.entity.Client;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация из ClientDTO в Client
 */
@Component
public class ClientFromClientDTOConverter implements Converter<ClientDTO, Client> {

    @Override
    public Client convert(ClientDTO clientDTO) {
        Client client = new Client();
        client.setFullName(clientDTO.getFullName());
        client.setPhone(clientDTO.getPhone());
        client.setClientId(clientDTO.getClientId());
        client.setPassportNumber(clientDTO.getPassportNumber());
        client.setEmail(clientDTO.getEmail());
        return client;
    }
}