package ru.clevertec.vasili.urusov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.vasili.urusov.model.dto.CardDTO;
import ru.clevertec.vasili.urusov.model.entity.Card;
import ru.clevertec.vasili.urusov.repository.CardRepository;
import ru.clevertec.vasili.urusov.service.CardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    @Transactional
    public void addCards(List<CardDTO> cardDTOList) {
        List<Card> cardList = new ArrayList<>();
        cardDTOList
                .forEach(cardDTO -> cardList.add(new Card(cardDTO.getNumber())));
        cardRepository.saveAll(cardList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardDTO> findAll() {
        List<CardDTO> cardDTOList = new ArrayList<>();
        cardRepository.findAll()
                .forEach(card -> cardDTOList.add(new CardDTO(card.getNumber())));
        return cardDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public CardDTO findById(Long id) {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if(cardOptional.isPresent()){
            return new CardDTO(cardOptional.get().getNumber());
        }else {
            throw new IllegalArgumentException("There isn't card with this id = " + id);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        cardRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existsByNumber(Integer number) {
        return cardRepository.existsByNumber(number);
    }
}
