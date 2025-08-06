package com.example.WebApp.service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.WebApp.model.Product;
import com.example.WebApp.repository.ProductRepo;


@Service
public class ProductService {
	
	@Autowired
	ProductRepo repo;

	public List<Product> getProducts(){
		//return prod;
		 System.out.println("Fetching products..."+ repo.findAll());
		return repo.findAll();
	}

	public Product getProductsId(int id) {
		System.out.println("Fetching products by id...");
		return repo.findById(id).orElse(null);
	}

	public Product addProduct(Product p, MultipartFile img) throws IOException {
		System.out.println("inside service");
		System.out.println("Received image: " + img.getContentType()+" "+ img.getBytes());
		 p.setImage(img.getBytes());
		 p.setImageType(img.getContentType());
		return repo.save(p);
	}

	public void updateProduct(Product p) {
	 repo.save(p);
		
	}
	public void deleteProduct(int id) {
		repo.deleteById(id);
	}

	public ResponseEntity<byte[]> getProductImageById(int id) {
		Product p = repo.findById(id).orElse(null);
		if (p == null || p.getImage() == null || p.getImageType() == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType(p.getImageType()))
				.body(p.getImage());
	}
}
