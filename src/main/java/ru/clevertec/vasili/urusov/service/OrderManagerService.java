package ru.clevertec.vasili.urusov.service;

import ru.clevertec.vasili.urusov.model.dto.OrderOfClientDTO;
import ru.clevertec.vasili.urusov.model.dto.Check;

public interface OrderManagerService {
    Check creatCheck(OrderOfClientDTO orderOfClientDTO);
}
