package com.beworth.bookHub;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

	// BookService 서비스 호출을 위해 서비스 빈 추가
	@Autowired
	BookService bookService;
	
	/**
	 * 책 목록 조회 페이지
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView list(@RequestParam Map<String, Object> map) {
	    
	    List<Map<String, Object>> list = this.bookService.list(map); // 목록 조회
	    
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("data", list); // 데이터 뷰에 전달하도록 mav 객체에 담기
	    
	    if (map.containsKey("keyword")) { // 검색어가있다면(=keyword HTTP 파라미터가 있다면)
	        mav.addObject("keyword", map.get("keyword"));
	    }
	    
	    mav.setViewName("/book/list");
	    return mav;
	}
	
	/** 
	 * 책 상세 조회 페이지
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
	    
		Map<String, Object> detailMap = this.bookService.detail(map); // 조회 결과 변수(detailMap)에 담기
	    
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("data", detailMap); // mav에 뷰에 전달할 데이터 담기
	    
	    String bookId = map.get("bookId").toString(); // pk인 bookId 담기
	    mav.addObject("bookId", bookId);
	    
	    mav.setViewName("/book/detail"); //view 경로지정
	    
	    return mav;
	}
	
	
	/** 
	 * 책 등록 페이지
	 * 어노테이션 정리
	 * @RequestMapping : 브라우저의 요청에 실행되는 자바 메소드 지정 
	 * 	- value : 브라우저의 주소(URI) 지정
	 * 	- method : http 요청 메소드. 데이터 변경이 없는 메소드 이므로 GET으로 지정(<->POST)
	 * ModelAndView : '컨트롤러가 반환할 데이터를 담당하는 Model'과 '화면을 담당하는 View'의 경로를 합쳐놓은 객체
	 * 				    생성자에 문자열 타입 파라미터가 입력되면 View의 경로로 간주한다
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		return new ModelAndView("book/create");
	}
	
	/** 
	 * 책 등록 
	 * 어노테이션 정리
	 * @RequestParam : HTTP 파라미터(=브라우저에서 서버로 전달하는 데이터)를 map 변수에 자동으로 바인딩.
	 *  - 스프링은 객체 타입이나 스칼라 타입일 경우 특별한 어노테이션 없이도 HTTP 파라미터를
	 *    자바 메소드의 파라미터로 변환해서 컨트롤러 메소드를 호출한다
	 *  -*Map 타입의 경우는 예외여서, @RequestParam 어노테이션을 붙여야만 
	 *    HTTP 파라미터의 값을 map 안에 바인딩 해준다.
	 *
	 * @RequestMapping
	 * - value 가 위와 동일하지만 method 가 다르므로 따로 찾아간다.
	 * - RequestMethod.POST : 데이터 변경이 일어나므로 HTTP 메소드는 POST 방식으로 처리
	 * - @PostMapping(value = "/create") 와 같다.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    String bookId = this.bookService.create(map); // 서비스 메소드 호출
	    
	    // setViewName 메소드 : 뷰의 경로 지정
	    // [redirect:] : [redirect:]로 시작하면 스프링은 뷰 파일을 찾는게 아니라, 웹페이지 주소를 변경한다.
	    if (bookId == null) {
	        mav.setViewName("redirect:/create"); // [/create] 경로로 웹페이지 주소 변경.
	    }else {
	    	/* 쿼리 스트링 : 주소창을 통해 파라미터가 서버로 전달되는 형태
	    	 * HTTP 규격의 쿼리 스트링 : URL 끝에 ? 로 시작, 각 항목은 & 로 연결, 개별항목 키와 값은 = 로 구분
	    	 * [/detail] : URL
	    	 * [?bookId=1] : 쿼리스트링 
	    	 * [/detail?bookId=1] : URI
	    	 */
	        mav.setViewName("redirect:/detail?bookId=" + bookId); // 상세페이지 이동	
	    }		

	    return mav;
	}
	
	
	/**
	 * 책 수정 페이지
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam Map<String, Object> map) {
	    Map<String, Object> detailMap = this.bookService.detail(map); //상세 데이터 조회
	    
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("data", detailMap);
	    mav.setViewName("/book/update");
	    return mav;
	}
	
	/**
	 * 책 수정
	 * @param map
	 * @return
	 * 
	 * @RequestMapping 
	 * = value 앞에 [/(슬래시)] 생략해도 똑같게(/update) 인지한다.
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    boolean isUpdateSuccess = this.bookService.edit(map); // 데이터 update	
	    
	    if (isUpdateSuccess) { //true = 하나의 책 정보만 수정
	        String bookId = map.get("bookId").toString();
	        mav.setViewName("redirect:/detail?bookId=" + bookId); // 변동사항 확인을 위해 바로 상세페이지로 이동
	    } else {
	        mav = this.update(map); 
	        // 갱신이 안되었을 경우, 갱신화면 보여주는 메소드 호출 (this.update = BookController.update)
	    }		

	    return mav;
	}
	
	/**
	 * 책 삭제
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    boolean isDeleteSuccess = this.bookService.remove(map);	// 데이터 delete	
	    
	    if (isDeleteSuccess) {			
	        mav.setViewName("redirect:/list"); // 목록 페이지로 리다이렉트
	    }else {
	        String bookId = map.get("bookId").toString();
	        mav.setViewName("redirect:/detail?bookId=" + bookId); // 삭제 실패시 상세페이지로 다시 이동
	    }

	    return mav;
	}
	
}
