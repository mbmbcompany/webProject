package sk.bielik.webProject.service;

import sk.bielik.webProject.entityDto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto);

    ProductDto getProductById(long id);

    List<ProductDto> getAllProducts();

    void deleteProductById(long id);

    ProductDto updateProduct(long id,ProductDto productDto);

    List<ProductDto> getAllProductsOrderedByPrice(boolean fromHighest);
}
