package ru.clevertec.vasili.urusov.service;

import ru.clevertec.vasili.urusov.model.dto.CardDTO;

import java.util.List;

public interface CardService {

    void addCards(List<CardDTO> cardDTOList);
    List<CardDTO> findAll();
    CardDTO findById(Long id);
    void deleteAll();
    Boolean existsByNumber(Integer number);
}
