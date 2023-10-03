package com.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dto.ProductionDTO;
import com.project.jpaRepository.ProductionEntityRepository;
import com.project.mapper.ProductionMapper;

@Service
public class ProductionService {
	
	private ProductionMapper productionMapper;
	
	private ProductionEntityRepository productionEntityRepository;
	
	@Autowired
	public ProductionService(ProductionEntityRepository productionEntityRepository, ProductionMapper productionMapper) {
	    this.productionEntityRepository = productionEntityRepository;
	    this.productionMapper = productionMapper;
	}
	
	// 페이징 처리를 위한 메서드
	public Page<Object[]> getAllProductionsWithJoin(int page, int pageOfContentCount) {
		PageRequest pageable = PageRequest.of(page, pageOfContentCount);
		return productionEntityRepository.findAllWithJoin(pageable);
	}
	

	public List<ProductionDTO> selectProduction(String search) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		return productionMapper.selectProduction(map);
	}
	
	public List<ProductionDTO> selectDate(String startDate, String endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);     
		return productionMapper.selectDate(map);
	}
	
	@Transactional
	 public void register(ProductionDTO productionDTO) {
	        try {
	            productionMapper.insertProduction(productionDTO);
	            productionMapper.insertProduct(productionDTO);
	            productionMapper.insertSell(productionDTO);
	        } catch (Exception e) {
	            e.printStackTrace();
	            // 오류 처리 로직 추가
	        }
	    }

	public int deleteProduction(List<String> pNum) {
		return productionMapper.deleteProduction(pNum);
	}

	public void ProductionModify(ProductionDTO productionDTO) {
		try {
            productionMapper.productionModify(productionDTO);
            productionMapper.productModify(productionDTO);
            productionMapper.sellModify(productionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 로직 추가
        }
	}

	public ProductionDTO PMSearch(String pNum) {
		return productionMapper.PMSearch(pNum);
	}


	

	
	
	
}
