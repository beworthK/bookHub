package com.beworth.bookHub;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository 
public class BookDao {

	/**
	 * @Repository : 데이터에 접근하는 클래스임을 명시. 스프링이 데이터를 관리하는 클래스로 인지해서 java bean 으로 등록해서 관리한다
	 * @Autowired : SqlSessionTemplate 타입 객체를 사용하게 위채 bookDao에 의존성 주입. 
	 */
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		// selectList : 결과를 목록(List)으로 가져온다. 첫번째는 쿼리 ID, 두번째 ID는 쿼리 파라미터
	    return this.sqlSessionTemplate.selectList("book.select_list", map);
	}
	
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		// selectOne : 데이터 하나만 가져오는 메소드 
		// Map<String, Object> : 매퍼 XML의 resultType 과 일치해야 한다.
	    return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}
	
	public int insert(Map<String, Object> map) {
		// insert : RDBMS 에서 insert 구문 return 값은 등록했으면 1, 실패하면 0
		// book : namespace, insert :SQL id
		return this.sqlSessionTemplate.insert("book.insert", map);
	}
	
	public int update(Map<String, Object> map) {
		// update : RDBMS 에서 UDPATE 구문 return 값은 업데이트 된 모든 행 수
	    return this.sqlSessionTemplate.update("book.update", map);
	}
	
	public int delete(Map<String, Object> map) {
		// delete : RDBMS 에서 DELETE 구문 return 값은 삭제된 모든 행 수
	    return this.sqlSessionTemplate.delete("book.delete", map);
	}
}
