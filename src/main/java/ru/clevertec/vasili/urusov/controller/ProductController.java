package ru.clevertec.vasili.urusov.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.vasili.urusov.model.dto.ProductDTO;
import ru.clevertec.vasili.urusov.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/list/add")
    public void addProducts(@RequestBody List<ProductDTO> productDTOList){
        log.info("add products {}", productDTOList);
        productService.addProducts(productDTOList);
    }

    @PostMapping("/one/add")
    public void addProduct(@RequestBody ProductDTO productDTO){
        log.info("add product in repository {}", productDTO);
        productService.addProduct(productDTO);
    }

    @GetMapping("/find/{id}")
    public ProductDTO findById(@PathVariable Long id){
        log.info("find product by id {}", id);
        return productService.findById(id);
    }

    @GetMapping("/all/discount/find")
    public List<ProductDTO> findAllByDiscount(@RequestParam Boolean discount){
        log.info("get product by discount {}", discount);
        return productService.findAllByDiscount(discount);
    }

    @GetMapping("/find/by/name/{name}")
    public ProductDTO findByName(@PathVariable String name){
        log.info("get name for finding product by name {}", name);
        return productService.findByName(name);
    }

    @GetMapping("find/all")
    public List<ProductDTO> findAll(){
        log.info("get all products");
        return productService.findAll();
    }

    @DeleteMapping("/all/delete")
    public void deleteAll(){
        log.info("delete all product");
        productService.deleteAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        log.info("delete product by id{}", id);
        productService.deleteById(id);
    }
}
