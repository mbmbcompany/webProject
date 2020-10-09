package sk.bielik.webProject.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.entityDto.ProductDto;

@Mapper
public interface ProductMapper {
    @Mapping(source ="title",target = "name")
    Product mapProductDtoToProduct(ProductDto productDto);
    @Mapping(source = "name",target = "title")
    ProductDto mapProductToProductDto(Product product);
}
