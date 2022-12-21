package ru.clevertec.vasili.urusov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.clevertec.vasili.urusov.model.dto.OrderOfClientDTO;
import ru.clevertec.vasili.urusov.service.CheckService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/check")
public class CheckController {

    private final CheckService checkService;

    @GetMapping(value = "/creat", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getCheck(@RequestBody OrderOfClientDTO orderOfClientDTO){
        log.info("creat check from order of client {}", orderOfClientDTO);
        String check = checkService.createCheck(orderOfClientDTO);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handler(IllegalArgumentException e){
        log.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }
}
