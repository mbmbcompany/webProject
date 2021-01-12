package sk.bielik.webProject.service;

import sk.bielik.webProject.entityDto.ProductBasicInfoDto;
import sk.bielik.webProject.entityDto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductBasicInfoDto addProduct(ProductDto productDto);

    ProductBasicInfoDto getProductById(long id);

    ProductDto getProductDtoById(long id);

    List<ProductBasicInfoDto> getAllProducts();

    void deleteProductById(long id);

    ProductDto updateProduct(long id, ProductDto productDto);

    List<ProductBasicInfoDto> getAllProductsOrderedByPrice(boolean fromHighest);

    ProductDto save(ProductDto productDto);
}
