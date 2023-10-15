package com.beworth.bookHub;

import java.util.List;
import java.util.Map;

public interface BookService {

	/**
	 * BookService : 서비스 인터페이스
	 * 메소드 시그니처 생성
	 * *시그니처 : 자바에서 시그니처는 메서드 명과 파라미터의 순서, 타입, 개수
	 */

	List<Map<String, Object>> list(Map<String, Object> map);
	
	Map<String, Object> detail(Map<String, Object> map);
	
	String create(Map<String, Object> map);
	
	boolean edit(Map<String, Object> map);
	
	boolean remove(Map<String, Object> map);

}
