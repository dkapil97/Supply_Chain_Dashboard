package com.kd.service;

import java.util.List;

import com.kd.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> getAllProducts();
	
	ProductDTO createProduct(ProductDTO productDTO);
	
	ProductDTO getProductById(Long id);
	
	ProductDTO updateProduct(Long id,ProductDTO productDTO);
	
	void deleteProductById(Long id);
}
