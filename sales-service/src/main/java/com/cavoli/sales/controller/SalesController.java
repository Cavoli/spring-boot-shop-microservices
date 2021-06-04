package com.cavoli.sales.controller;


import com.cavoli.sales.common.messages.BaseResponse;
import com.cavoli.sales.dto.ProductDTO;
import com.cavoli.sales.dto.SalesDTO;
import com.cavoli.sales.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Validated
@RestController
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	private SalesService salesService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/find")
	public ResponseEntity<List<SalesDTO>> getAllSales() {
		List<SalesDTO> list = salesService.findSalesList();
		return new ResponseEntity<List<SalesDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/find/by-id")
	public ResponseEntity<SalesDTO> getSalesById(@RequestParam Long id) {
		SalesDTO list = salesService.findBySalesId(id);
		return new ResponseEntity<SalesDTO>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/{salesId}")
	public ResponseEntity<SalesDTO> createOrUpdateSales(@RequestBody SalesDTO salesDTO,
															@PathVariable("salesId") Long salesId) {
		ProductDTO productDTO = new ProductDTO();
		try {
			String url = "http://product-server/product-api/product/" + salesId;
			ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);
			productDTO = response.getBody();
		} catch (Exception e) {
			System.out.println(e);
		}
		salesDTO.setProducts(productDTO.getProductName());
		this.salesService.createOrUpdateSales(salesDTO);
		return new ResponseEntity<SalesDTO>(salesDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<BaseResponse> deleteSalesById(@PathVariable("id") Long id) {
		BaseResponse response = salesService.deleteSales(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
