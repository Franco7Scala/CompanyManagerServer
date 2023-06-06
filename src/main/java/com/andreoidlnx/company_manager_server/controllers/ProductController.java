package com.andreoidlnx.company_manager_server.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.andreoidlnx.company_manager_server.entities.Product;
import com.andreoidlnx.company_manager_server.entities.ProductDetail;
import com.andreoidlnx.company_manager_server.entities.ProductState;
import com.andreoidlnx.company_manager_server.entities.ProductTransition;
import com.andreoidlnx.company_manager_server.entities.State;
import com.andreoidlnx.company_manager_server.entities.User;
import com.andreoidlnx.company_manager_server.services.ProductService;
import com.andreoidlnx.company_manager_server.services.StateService;
import com.andreoidlnx.company_manager_server.supports.exceptions.ProductNotExistException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StateService stateService;

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product){
        try{
            productService.addProduct(product);
            return new ResponseEntity<>("PRODUCT_ADDED", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("ERROR_PRODUCT_NOT_ADDED", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addProductDetail")
    public ResponseEntity<?> addProductDetail(@Valid @RequestBody ProductDetail productDetail, @Valid @RequestBody State state, @Valid @RequestBody User user) {
        try{
            productService.addProductDetail(productDetail, state, user);
            return new ResponseEntity<>("PRODUCT_DETAIL_ADDED", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("ERROR_PRODUCT_DETAIL_NOT_ADDED", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/editProductDetail")
    public ResponseEntity<?> editProductDetail(@Valid @RequestBody ProductDetail productDetail) {
        try{
            productService.editProductDetail(productDetail);
            return new ResponseEntity<>("PRODUCT_DETAIL_EDITED", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("ERROR_PRODUCT_DETAIL_NOT_EDITED", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addProductState")
    public ResponseEntity<?> addProductState(@Valid @RequestBody ProductState productState) {
        try{
            productService.addProductState(productState);
            return new ResponseEntity<>("PRODUCT_STATE_ADDED", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("ERROR_PRODUCT_STATE_NOT_ADDED", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/addTransition")
    public ResponseEntity<?> addTransition(@Valid @RequestBody ProductTransition productTransition) {
        try{
            productService.addTransition(productTransition);
            return new ResponseEntity<>("TRANSITION_ADDED", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("ERROR_TRANSITION_NOT_ADDED", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        try{
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        }
        catch (ProductNotExistException e){
            return new ResponseEntity<>("ERROR_PRODUCT_NOT_EXISTS", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getProductDetailByBarCodePackage/{barCode}/{onlyAvailable}")
    public List<ProductDetail> getProductDetailByBarCodePackage(@PathVariable String barCode, @PathVariable boolean onlyAvailable) {
        return productService.getProductDetailByBarCodePackage(barCode, onlyAvailable);
    }

    @GetMapping("getProductDetailByBarCodeSingle/{barCode}/{onlyAvailable}")
    public List<ProductDetail> getProductDetailByBarCodeSingle(@PathVariable String barCode, @PathVariable boolean onlyAvailable) {
        return productService.getProductDetailByBarCodeSingle(barCode, onlyAvailable);
    }

    @GetMapping("/getProductTransitions")
    public List<ProductTransition> getProductTransitions() {
        return productService.getProductTransitions();
    }

    @GetMapping("/getAllProductsDetail")
    public List<ProductDetail> getAllProductsDetail() {
        return productService.getAllProductsDetail();
    }

    @GetMapping("/getFullProductsDetailByProductName/{name}")
    public List<ProductDetail> getFullProductsDetailByProductName(@PathVariable String name) {
        return productService.getFullProductsDetailByProductName(name);
    }

    @GetMapping("/getProductsDetailByProductName/{name}")
    public List<ProductDetail> getProductsDetailByProductName(@PathVariable String name) {
        return productService.getProductsDetailByProductName(name);
    }

    @GetMapping("/getProductByName/{name}")
    public List<Product> getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }

}
