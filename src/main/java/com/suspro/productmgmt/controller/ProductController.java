package com.suspro.productmgmt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suspro.productmgmt.exception.ResourceNotFoundException;
import com.suspro.productmgmt.model.Product;
import com.suspro.productmgmt.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	// get all products
	@GetMapping("/")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}		

	// create product rest api
	@PostMapping("/")
	@Produces("application/json")
	@Consumes("application/json")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}

	// get product by id rest api
	@GetMapping("/employees/{empid}")
	public List<Product> getProductById(@PathVariable("empid") long empid) {
		List<Product> products = productRepository.findAll();
		return products.stream().filter(product -> product.getEmployeeId() == empid).collect(Collectors.toList());
	}

	// update product rest api

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product productDetails){
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));

		product.setProductName(productDetails.getProductName());
		product.setProductType(productDetails.getProductType());
		product.setProductPrice(productDetails.getProductPrice());

		Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}

	// delete product rest api
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable long id){
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));

		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


}
