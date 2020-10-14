package sk.bielik.webProject.service.serviceImpl;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import sk.bielik.webProject.entityDto.ProductBasicInfoDto;
import sk.bielik.webProject.entityDto.ProductDto;
import sk.bielik.webProject.repository.repositoryImp.ProductRepositoryImpl;
import sk.bielik.webProject.service.ProductMapperImpl;
import sk.bielik.webProject.service.ProductService;

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
    public ProductBasicInfoDto addProduct(ProductDto productDto) {
        return productMapper.mapProductToProductBasicInfoDto(productRepository.addProduct(productMapper.mapProductDtoToProduct(productDto)));
    }

    @Override
    public ProductBasicInfoDto getProductById(long id) {
        return productMapper.mapProductToProductBasicInfoDto(productRepository.getProductById(id));
    }

    @Override
    public ProductDto getProductDtoById(long id) {
        return productMapper.mapProductToProductDto(productRepository.getProductById(id));
    }

    @Override
    public List<ProductBasicInfoDto> getAllProducts() {
        return productRepository.getAllProducts().stream().map(product -> productMapper.mapProductToProductBasicInfoDto(product)).collect(Collectors.toList());
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
        productDto.setAddedToTrolley(updatedProductDto.getAddedToTrolley());
        return productMapper.mapProductToProductDto(productRepository.addProduct(productMapper.mapProductDtoToProduct(productDto)));


    }

    @Override
    public List<ProductBasicInfoDto> getAllProductsOrderedByPrice(@NotNull boolean fromLowest) {
        List<ProductBasicInfoDto> productDtos=productRepository.getAllProducts().stream().map(product -> productMapper.mapProductToProductBasicInfoDto(product)).collect(Collectors.toList());
        if (fromLowest){
             productDtos.sort(Comparator.comparing(ProductBasicInfoDto::getPrice));
             return productDtos;
        }else {
            Comparator<ProductBasicInfoDto> salaryComparator = Comparator.comparing(ProductBasicInfoDto::getPrice);
            productDtos.sort(salaryComparator.reversed());
            return productDtos;
        }

    }
}
