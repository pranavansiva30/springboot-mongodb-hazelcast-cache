package com.wavesdev.cache.controller;

/**
 * Created by pranavan on 2/27/18.
 */
import com.wavesdev.cache.model.Product;
import com.wavesdev.cache.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @PostMapping("product")
    public Product addProduct(@RequestBody Product product) {
        LOGGER.info("addProduct called for name {}",product.getName());
        productService.save(product);
        return productService.getProduct(product.getId());
    }
    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable("id") String id) {
        LOGGER.info("getProduct called for id {}", id);
        return productService.getProduct(id);
    }

    @PutMapping("product/{id}")
    public Product updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        LOGGER.info("updateProduct called for id {}", id);
        product.setId(id);
        Product updatedProduct=productService.updateProduct(product);
        return updatedProduct;
    }
    @DeleteMapping("product/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        LOGGER.info("deleteProduct called for id {}", id);
        try {
            productService.delete(id);
        }
        catch(Exception e){
            return "FAIL";

        }
      return "SUCCESS";
    }
    @GetMapping("product/cache/clear")
    public String clearProductCache() {
        LOGGER.info("delete All cache");
        try {
            productService.clearCache();
        }
        catch(Exception e){
            return "FAIL";

        }
        return "SUCCESS";
    }
}