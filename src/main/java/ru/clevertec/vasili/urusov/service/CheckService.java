package ru.clevertec.vasili.urusov.service;

import ru.clevertec.vasili.urusov.model.dto.OrderOfClientDTO;


public interface CheckService {

    String createCheck(OrderOfClientDTO orderOfClientDTO);
}
