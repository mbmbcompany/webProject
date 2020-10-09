package sk.bielik.webProject.repository.repositoryImp;

import org.springframework.stereotype.Repository;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.repository.ProductRepository;

import java.util.List;

@Repository
public class ProductRepositoryImpl {

    private final ProductRepository productRepository;

    public ProductRepositoryImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product getProductById(long id){
        return productRepository.findById(id).get();
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void deleteProductById(long id){
        productRepository.deleteById(id);
    }

}
