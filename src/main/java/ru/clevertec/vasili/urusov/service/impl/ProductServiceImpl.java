package ru.clevertec.vasili.urusov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.vasili.urusov.model.dto.ProductDTO;
import ru.clevertec.vasili.urusov.model.entity.Product;
import ru.clevertec.vasili.urusov.repository.ProductRepository;
import ru.clevertec.vasili.urusov.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void addProducts(List<ProductDTO> productDTOList) {
        List<Product> productList = new ArrayList<>();
        productDTOList
                .forEach(p -> productList
                        .add(new Product(p.getName(), p.getPrice(), p.getDiscount())));
        productRepository.saveAll(productList);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
        productRepository
                .save(new Product(productDTO.getName(),
                        productDTO.getPrice(),
                        productDTO.getDiscount()));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            return new ProductDTO(product.getName(), product.getPrice(), product.getDiscount());
        }else {
            throw new IllegalArgumentException("There isn't product with this id = " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAllByDiscount(Boolean discount) {
        List<ProductDTO> productDTOsByDiscount = new ArrayList<>();
        productRepository.findAllByDiscount(discount)
                .forEach(p -> productDTOsByDiscount
                        .add(new ProductDTO(p.getName(),p.getPrice(),p.getDiscount())));

        return productDTOsByDiscount;
    }

    @Override
    @Transactional
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
