package com.example.WebApp.controllers;

import com.example.WebApp.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.WebApp.model.Product;

import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService pd; // Autowired annotation will create the object in Spring Boot.

    @GetMapping("/product")
    public ResponseEntity<List<Product>> productData() {
    
        return new ResponseEntity<>(pd.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = pd.getProductsId(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(
    	    @RequestPart("product") Product p,
    	    @RequestPart("imageFile") MultipartFile img) throws IOException{
    	System.out.println("Received product: " + p);
        System.out.println("Received image: " + img.getContentType()+" "+ img.getBytes());
    	try {
    		System.out.println("inside try");
    		Product pro=pd.addProduct(p, img);
    		return new ResponseEntity<>(pro, HttpStatus.OK);
    	}
    	catch(Exception e){
    	return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    
    	}
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        return pd.getProductImageById(id);
    }
  
    @PutMapping("/product")
    public ResponseEntity<?> updateProduct(@RequestBody Product p) {
        pd.updateProduct(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        pd.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
