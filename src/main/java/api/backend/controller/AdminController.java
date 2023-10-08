package api.backend.controller;

import api.backend.dto.ProductDTO;
import api.backend.dto.ProductRequest;
import api.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    //delete users
    //count number of users on the system

    //see items being shipped
    //check stock count

    //create items
    @PostMapping("/add_product")
    public ResponseEntity<ProductDTO> createProducts(@RequestBody ProductRequest productRequest){
        ProductDTO product=productService.newProduct(productRequest);
        return ResponseEntity.ok(product);
    }
    //remove items
    @DeleteMapping("/remove_product/{SKU}")
    public ResponseEntity<String> removeProduct(@PathVariable("SKU") ProductRequest productRequest){
        productService.deleteProduct(productRequest);
        return ResponseEntity.ok("Successfully removed item!");
    }
    //update items
}
