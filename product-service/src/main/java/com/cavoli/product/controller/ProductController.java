package com.cavoli.product.controller;

import com.cavoli.product.common.messages.BaseResponse;
import com.cavoli.product.dto.ProductDTO;
import com.cavoli.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "")
	public ResponseEntity<List<ProductDTO>> getProducts() {
		List<ProductDTO> list = productService.getProductList();
		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/{productId}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("productId") Long id) {
		ProductDTO product = new ProductDTO();
		product = productService.findByProductId(id);
		return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
	}

	@PostMapping(value = "")
	public ResponseEntity<BaseResponse> createProduct(@Valid @RequestBody ProductDTO productDTO) {
		BaseResponse response = productService.createProduct(productDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/{productId}")
	public ResponseEntity<BaseResponse> updateProduct(@PathVariable("productId") Long id,
													  @Valid @RequestBody ProductDTO productDTO) {
		BaseResponse response = productService.updateProduct(id, productDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{productId}")
	public ResponseEntity<BaseResponse> deleteProductById(@PathVariable("productId") Long id) {
		BaseResponse response = productService.deleteProductById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
