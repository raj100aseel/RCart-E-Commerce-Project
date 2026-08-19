package com.ecommerce.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.entity.Product;
import com.ecommerce.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		return productService.getProductById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
		return ResponseEntity.ok(productService.getByCategory(category));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> search(@RequestParam String keyword) {
		return ResponseEntity.ok(productService.search(keyword));
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		return ResponseEntity.ok(productService.updateProduct(id, product));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/reduce-stock")
	public ResponseEntity<Product> reduceStock(@PathVariable Long id, @RequestParam Integer quantity) {
		return ResponseEntity.ok(productService.reduceStock(id, quantity));
	}
	
}








