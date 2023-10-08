package api.backend.services;

import api.backend.dto.ProductDTO;
import api.backend.dto.ProductRequest;
import api.backend.entities.Category;
import api.backend.entities.Products;
import api.backend.mapper.ProductMapper;
import api.backend.repository.CategoryRepository;
import api.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMapper productMapper;

    public ProductDTO newProduct(ProductRequest productRequest) {
        //check if the product already exists
        Optional<Products> productSKU=productRepository.findBySKU(productRequest.SKU());
        if (productSKU.isPresent()){
            throw new RuntimeException(HttpStatus.FOUND.toString());
        }
        //create a category
        Category category=new Category(productRequest.category());
        HashSet<Category> productCategory=new HashSet<>();
        productCategory.add(category);
        categoryRepository.save(category);
        //create a new product
        Products products=productMapper.prodRequestToProducts(productRequest);
//        products.setCategory(category);

        Products newProduct=productRepository.save(products);
        return productMapper.toProductDto(newProduct);
    }

    //method to get all products

    //method to get product by name

    //method to Add a product

    //method to remove product
    public void deleteProduct(ProductRequest productRequest) {
        //find product by skuNumber
        if(productRepository.findBySKU(productRequest.SKU()).isPresent()){
            productRepository.deleteBySKU(productRequest.SKU());
        }
        throw new RuntimeException(HttpStatus.NOT_FOUND.toString());
    }
    //method to update a product
}
