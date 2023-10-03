package com.project.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.dto.ProductionDTO;
import com.project.jpaRepository.ProductionEntityRepository;
import com.project.service.ProductionService;
import com.project.vo.PagingVO;

@Controller
public class ProductionController {
	
	public ProductionController() {
		
	}	
	
	private ProductionService productionService;
	
	private ProductionEntityRepository productionEntityRepository;
	
	@Autowired
	public ProductionController(ProductionService productionService,
			ProductionEntityRepository productionEntityRepository) {
		this.productionService = productionService;
		this.productionEntityRepository = productionEntityRepository;
	}
	//페이징처리
	@RequestMapping("/PM")
	public ModelAndView ProductionMain(@RequestParam(defaultValue = "0") int page, ModelAndView view) {
	    // 한 페이지에 표시할 게시글 개수
	    int pageOfContentCount = 10;

	    // 현재 페이지 번호
	    int currentPageNo = page + 1; // 0부터 시작하는 페이지 번호를 1부터 시작하도록 변환
	    
	    Page<Object[]> resultPage = productionService.getAllProductionsWithJoin(page, pageOfContentCount);
	    System.out.println(resultPage.toString());
	    List<Object[]> resultList = resultPage.getContent();
	    System.out.println(resultList.toString());

	    // 페이징 정보를 담은 VO 생성
	    PagingVO pagingVO = new PagingVO(
	        (int) resultPage.getTotalElements(),  // 전체 게시글 개수
	        currentPageNo,                      // 현재 페이지 번호
	        pageOfContentCount,                 // 한 페이지당 출력할 게시글 개수
	        5                                   // 페이지 번호 개수 (예: 1 2 3 4 5)
	    );

	    view.addObject("select_list", resultList); // 수정된 부분
	    view.addObject("pagingVO", pagingVO);

	    view.setViewName("/production/ProductionMain");

	    return view;
	}
	//검색
	@RequestMapping("/pm/search.do")
	public ResponseEntity<String> selectProduction(String search){
		List<ProductionDTO> list = productionService.selectProduction(search);
		return new ResponseEntity(list,HttpStatus.OK);
	}
	//날짜검색
	@RequestMapping("/date/search.do")
	public ResponseEntity<String> selectDate(String startDate,String endDate){
		List<ProductionDTO> list = productionService.selectDate(startDate,endDate);		
		System.out.println(list);
		return new ResponseEntity(list,HttpStatus.OK);
	}
	//등록
	//1. ProductionRegister로 이동하는 메서드 작성
	@RequestMapping("/PMRegister")
	public String registerView() {
		return "/production/ProductionRegister";
	}
	//2. 등록할 정보를 받아서 DB에 등록하는 메서드 작성
	@RequestMapping("/production/register/action")
	public String register(ProductionDTO productionDTO,HttpServletRequest request) {
		
		 // UUID 생성
        UUID productionNumUUID = UUID.randomUUID();
        String productionNum = productionNumUUID.toString(); // UUID를 문자열로 변환
        productionDTO.setProductionNum(productionNum);
        System.out.println(productionNum);
        
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 형식에 맞게 변경
	
		    try {
		        Date dateRegister = dateFormat.parse(productionDTO.getDateRegister());
		        Date dateFinish = dateFormat.parse(productionDTO.getDateFinish());
		        System.out.println(dateRegister);
		        System.out.println(dateFinish);
		        productionDTO.setDateRegister(dateFormat.format(dateRegister)); // 문자열을 다시 설정
		        productionDTO.setDateFinish(dateFormat.format(dateFinish)); // 문자열을 다시 설정	        

		    } catch (ParseException e) {
		        e.printStackTrace();
		        // 날짜 파싱에 실패한 경우 예외 처리

		       
		    }
		    
		    productionService.register(productionDTO);
	
		    
	    return "redirect:/PM";
	}
	// pNum 검색해서 innerHtml로 입력바로 받기
	@ResponseBody
	@RequestMapping(value = "/PMRegister/searchByPNum", method = RequestMethod.POST)
	public ProductionDTO PMSearch(@RequestParam("pNum") String pNum) {
	    ProductionDTO resultDTO  = productionService.PMSearch(pNum);
		System.out.println("resultDTO:" + resultDTO);
		
		return resultDTO;
	}
	//삭제
	@RequestMapping("/pm/delete")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> delete(@RequestBody List<String> pNums) {
	    System.out.println(pNums);
	    int result = productionService.deleteProduction(pNums);
	    Map<String, Object> response = new HashMap<>();
	    response.put("count", result);
	    if (result == 0) {
	        response.put("message", "데이터 삭제 실패");
	    } else {
	        response.put("message", "데이터 삭제 성공");
	    }

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	//수정
	//1. ProductionModify로 이동하는 메서드 작성
	@RequestMapping("/PMModify")
	public String modifyView() {
			return "/production/ProductionModify";
	}
	//2. 등록할 정보를 받아서 DB에 등록하는 메서드 작성
	@RequestMapping("/production/modify")
	public String ProductionModify(ProductionDTO productionDTO,@RequestParam("phase") String phaseValue) {
		productionDTO.setPhase(phaseValue);
		System.out.println(phaseValue);
		 // UUID 생성
        UUID productionNumUUID = UUID.randomUUID();
        String productionNum = productionNumUUID.toString(); // UUID를 문자열로 변환
        productionDTO.setProductionNum(productionNum);
        System.out.println(productionNum);
        
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 형식에 맞게 변경
	
		    try {
		        Date dateRegister = dateFormat.parse(productionDTO.getDateRegister());
		        Date dateFinish = dateFormat.parse(productionDTO.getDateFinish());
		        System.out.println(dateRegister);
		        System.out.println(dateFinish);
		        productionDTO.setDateRegister(dateFormat.format(dateRegister)); // 문자열을 다시 설정
		        productionDTO.setDateFinish(dateFormat.format(dateFinish)); // 문자열을 다시 설정
		        

		        // DB에 전달과정
		        productionService.ProductionModify(productionDTO);

		    } catch (ParseException e) {
		        e.printStackTrace();
		        // 날짜 파싱에 실패한 경우 예외 처리
		       
		    }
		return "redirect:/PM";
		
	}

	
	
}

