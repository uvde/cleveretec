package ru.clevertec.vasili.urusov.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.vasili.urusov.model.dto.CardDTO;
import ru.clevertec.vasili.urusov.service.CardService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cards")
public class CardController{

    private final CardService cardService;

    @PostMapping("/list/add")
    public void addCards(@RequestBody List<CardDTO> cardDTOList) {
        log.info("add list of cards {}", cardDTOList);
        cardService.addCards(cardDTOList);
    }

    @GetMapping("/all/find")
    public List<CardDTO> findAll() {
        log.info("get all cards");
        return cardService.findAll();
    }

    @GetMapping("/{id}")
    public CardDTO findById(@PathVariable Long id) {
        log.info("get card by id {}", id);
        return cardService.findById(id);
    }

    @DeleteMapping("/all/delete")
    public void deleteAll() {
        log.info("delete all cards");
        cardService.deleteAll();
    }

}
