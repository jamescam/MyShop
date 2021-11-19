package dev.huai.controllers;

import dev.huai.models.Product;
import dev.huai.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    @ResponseBody
    public ResponseEntity<?> addNewProduct(@RequestBody Product product){
        Boolean result = productService.addProduct(product);
        if(result == false){
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
        else{
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
    }

    @GetMapping("/product")
    @ResponseBody
    public ResponseEntity<?> getAllProduct(){
        ArrayList<Product> resultSet = productService.getAllProduct();
        if(resultSet == null){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }else{
            return new ResponseEntity<>(resultSet, HttpStatus.CREATED);
        }
    }
}
