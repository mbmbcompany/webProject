package sk.bielik.webProject.service.serviceImpl;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import sk.bielik.webProject.entity.enums.ProductGroup;
import sk.bielik.webProject.entityDto.ProductDto;
import sk.bielik.webProject.repository.repositoryImp.ProductRepositoryImpl;
import sk.bielik.webProject.service.ProductMapperImpl;
import sk.bielik.webProject.service.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepositoryImpl productRepository;

    private final ProductMapperImpl productMapper;

    public ProductServiceImpl(ProductRepositoryImpl productRepository, ProductMapperImpl productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return productMapper.mapProductToProductDto(productRepository.addProduct(productMapper.mapProductDtoToProduct(productDto)));
    }

    @Override
    public ProductDto getProductById(long id) {
        return productMapper.mapProductToProductDto(productRepository.getProductById(id));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.getAllProducts().stream().map(product -> productMapper.mapProductToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteProductById(id);
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto updatedProductDto) {
        ProductDto productDto=productMapper.mapProductToProductDto(productRepository.getProductById(id));
        productDto.setDescription(updatedProductDto.getDescription());
        productDto.setNumberOfPiecesInStock(updatedProductDto.getNumberOfPiecesInStock());
        productDto.setPrice(updatedProductDto.getPrice());
        productDto.setProductGroup(updatedProductDto.getProductGroup());
        productDto.setTitle(updatedProductDto.getTitle());
        return productMapper.mapProductToProductDto(productRepository.addProduct(productMapper.mapProductDtoToProduct(productDto)));


    }

    @Override
    public List<ProductDto> getAllProductsOrderedByPrice(@NotNull boolean fromLowest) {
        List<ProductDto> productDtos=productRepository.getAllProducts().stream().map(product -> productMapper.mapProductToProductDto(product)).collect(Collectors.toList());
        if (fromLowest){
             productDtos.sort(Comparator.comparing(ProductDto::getPrice));
             return productDtos;
        }else {
            Comparator<ProductDto> salaryComparator = Comparator.comparing(ProductDto::getPrice);
            productDtos.sort(salaryComparator.reversed());
            return productDtos;
        }

    }
}
