package sk.bielik.webProject.service;

import org.mapstruct.Mapper;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.entityDto.ProductBasicInfoDto;
import sk.bielik.webProject.entityDto.ProductDto;

@Mapper
public interface ProductMapper {

    Product mapProductDtoToProduct(ProductDto productDto);

    ProductDto mapProductToProductDto(Product product);

    ProductBasicInfoDto mapProductDtoToProductBasicInfoDto(ProductDto productDto);

    ProductDto mapProductBasicInfoDtoToProductDto(ProductBasicInfoDto productBasicInfoDto);

    Product mapProductBasicInfoDtoToProduct(ProductBasicInfoDto productBasicInfoDto);

    ProductBasicInfoDto mapProductToProductBasicInfoDto(Product product);

}
