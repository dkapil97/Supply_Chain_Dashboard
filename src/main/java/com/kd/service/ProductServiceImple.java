package com.kd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kd.dto.ProductDTO;
import com.kd.exception.ResourceNotFoundException;
import com.kd.model.Product;
import com.kd.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImple implements ProductService {

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<ProductDTO> getAllProducts() {

		return productRepository
				.findAll()
				.stream()
				.map(this::convertDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product product = convertToEntity(productDTO);
		Product save = productRepository.save(product);
		return convertDTO(product);
	}

	

	@Override
	public ProductDTO getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with:"+id));
		
		return convertDTO(product);
	}

	@Override
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product not found with:"+id));
		existingProduct.setName(productDTO.getName());
		existingProduct.setInventories(productDTO.getInventories());
		existingProduct.setPrice(productDTO.getPrice());
		
		Product updated = productRepository.save(existingProduct);
		
		return convertDTO(updated);
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		Product existing = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with:"+id));
		productRepository.delete(existing);
	}

	// Helper class 
	private ProductDTO convertDTO(Product product) {
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

		productDTO.setSupplierName(product.getSupplier().getName());
		if (product.getInventories() != null) {
			productDTO.setInventoryQuantity(product.getInventories().get(0).getQuantity());
		}
		return productDTO;
	}
	
	 private Product convertToEntity(ProductDTO dto) {
	        return modelMapper.map(dto, Product.class);
	    }
}
