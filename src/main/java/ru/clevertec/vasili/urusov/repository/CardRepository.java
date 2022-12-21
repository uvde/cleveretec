package ru.clevertec.vasili.urusov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.vasili.urusov.model.entity.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    Boolean existsByNumber(Integer number);
}
