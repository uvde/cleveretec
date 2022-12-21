package ru.clevertec.vasili.urusov.service;

import ru.clevertec.vasili.urusov.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void deleteAll();
    void addProducts(List<ProductDTO> productDTOList);
    void deleteById(Long id);
    void addProduct(ProductDTO productDTO);
    ProductDTO findById(Long id);
    List<ProductDTO> findAllByDiscount(Boolean discount);

}
