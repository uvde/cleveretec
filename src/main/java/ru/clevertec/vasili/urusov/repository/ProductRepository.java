package ru.clevertec.vasili.urusov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.vasili.urusov.model.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAllByDiscount(Boolean discount);
    Optional<Product> findByName(String name);
}
