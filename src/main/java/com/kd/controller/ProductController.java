package com.kd.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd.auth.UserService;
import com.kd.dto.ProductDTO;
import com.kd.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	private final UserService userService;
	@GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
		ProductDTO productById = productService.getProductById(id);
		return new ResponseEntity<>(productById,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
		ProductDTO product = productService.createProduct(productDTO);
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updatedProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
		ProductDTO updateProduct = productService.updateProduct(id, productDTO);
		return new ResponseEntity<ProductDTO>(updateProduct,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
		productService.deleteProductById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
