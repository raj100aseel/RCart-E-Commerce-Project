package com.ecommerce.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	public Optional<Product> getProductById(Long id){
		return productRepository.findById(id);
	}
	public List<Product> getByCategory(String category){
		return productRepository.findByCategoryIgnoreCase(category);
	}
	public List<Product> search(String keyword) {
		return productRepository.findByNameContainingIgnoreCase(keyword);
	}
	
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	public Product updateProduct(Long id, Product updated) {
		Product existing = productRepository.findById(id)
							.orElseThrow(() -> new RuntimeException("Product not found with id: "+id));
		existing.setName(updated.getName());
		existing.setDescription(updated.getDescription());
		existing.setPrice(updated.getPrice());
		existing.setImageUrl(updated.getImageUrl());
		existing.setCategory(updated.getCategory());
		existing.setStockQuantity(updated.getStockQuantity());
		return productRepository.save(existing);
		
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public Product reduceStock(Long id, Integer quantity) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: "+id));
		if (product.getStockQuantity() < quantity)
			throw new RuntimeException("Insufficient stock for product: "+product.getName());
		product.setStockQuantity(product.getStockQuantity() - quantity);
		return productRepository.save(product);
	}
}
















