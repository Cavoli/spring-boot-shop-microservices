package com.cavoli.sales.service;

import com.cavoli.sales.common.exceptions.RecordNotFoundException;
import com.cavoli.sales.common.messages.BaseResponse;
import com.cavoli.sales.common.messages.CustomMessage;
import com.cavoli.sales.common.utils.Topic;
import com.cavoli.sales.dto.SalesDTO;
import com.cavoli.sales.model.Sales;
import com.cavoli.sales.repo.SalesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalesService {

	@Autowired
	private SalesRepo salesRepo;

	public List<SalesDTO> findSalesList() {
		return salesRepo.findAll().stream().map(this::copySalesEntityToDto).collect(Collectors.toList());
	}

	public SalesDTO findBySalesId(Long salesId) {
		Sales userEntity = salesRepo.findById(salesId)
				.orElseThrow(() -> new RecordNotFoundException("Sales id '" + salesId + "' does not exist !"));
		return copySalesEntityToDto(userEntity);
	}

	public BaseResponse createOrUpdateSales(SalesDTO salesDTO) {
		Sales sales = copySalesDtoToEntity(salesDTO);
		salesRepo.save(sales);
		return new BaseResponse(Topic.Sales.getName() + CustomMessage.SAVE_SUCCESS_MESSAGE, HttpStatus.CREATED.value());

	}

	public BaseResponse deleteSales(Long salesId) {
		if (salesRepo.existsById(salesId)) {
			salesRepo.deleteById(salesId);
		} else {
			throw new RecordNotFoundException("No record found for given id: " + salesId);
		}
		return new BaseResponse(Topic.Sales.getName() + CustomMessage.DELETE_SUCCESS_MESSAGE, HttpStatus.OK.value());
	}


	private SalesDTO copySalesEntityToDto(Sales sales) {
		SalesDTO salesDTO = new SalesDTO();
		BeanUtils.copyProperties(sales, salesDTO);
		return salesDTO;
	}

	private Sales copySalesDtoToEntity(SalesDTO salesDTO) {
		Sales userEntity = new Sales();
		BeanUtils.copyProperties(salesDTO, userEntity);
		return userEntity;
	}

}
