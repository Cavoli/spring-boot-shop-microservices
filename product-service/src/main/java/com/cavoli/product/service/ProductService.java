package com.cavoli.product.service;


import com.cavoli.product.common.exceptions.RecordNotFoundException;
import com.cavoli.product.common.messages.BaseResponse;
import com.cavoli.product.common.messages.CustomMessage;
import com.cavoli.product.common.utils.Topic;
import com.cavoli.product.dto.ProductDTO;
import com.cavoli.product.model.Product;
import com.cavoli.product.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<ProductDTO> getProductList() {
		return productRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	public ProductDTO findByProductId(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RecordNotFoundException("Product id '" + productId + "' does not exist !"));
		return entityToDTO(product);
	}

	public BaseResponse createProduct(ProductDTO productDTO) {
		Product product = DTOToEntity(productDTO);
		productRepository.save(product);
		return new BaseResponse(Topic.PRODUCT.getName() + CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
	}

	public BaseResponse updateProduct(Long id,ProductDTO productDTO) {
		Product product = this.productRepository.findById(id).orElse(null);
		product.setProductId(productDTO.getProductId());
		product.setProductName(productDTO.getProductName());
		product.setProductPrice(productDTO.getProductPrice());
		productRepository.save(product);
		return new BaseResponse(Topic.PRODUCT.getName() + CustomMessage.UPDATE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());
	}

	public BaseResponse deleteProductById(Long productId) {
		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
		} else {
			throw new RecordNotFoundException("No record found for given id: " + productId);
		}
		return new BaseResponse(Topic.PRODUCT.getName() + CustomMessage.DELETE_SUCCESS_MESSAGE, HttpStatus.OK.value());
	}

	private ProductDTO entityToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(product, productDTO);
		return productDTO;
	}

	private Product DTOToEntity(ProductDTO productDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(productDTO, product);
		return product;
	}

}
