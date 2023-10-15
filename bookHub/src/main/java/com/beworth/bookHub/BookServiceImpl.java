package com.beworth.bookHub;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

	/** @Service
	 *  서비스 클래스는 비즈니스 클래스가 위치하는 곳
	 *  스프링 MVC 구조에서 서비스 클래스는 컨트롤러-DAO 를 연결하는 역할을 한다.
	 *  
	 *  @Autowired
	 *  BookDao 인스턴스 주입받기
	 */
	
	@Autowired
	BookDao bookDao;
	
	
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map){
	    return this.bookDao.selectList(map);
	}
	
	
	@Override
	public Map<String, Object> detail(Map<String, Object> map){
	    return this.bookDao.selectDetail(map);
	}
	
	/**
	 * @Override : 상위 인터페이스(BookService)에 정의된 것을 재정의.
	 */
	@Override
	public String create(Map<String, Object> map) {
	    
		int affectRowCount = this.bookDao.insert(map);
	    
		// insert 문은 성공하면 1, 실패하면 0 리턴
	    if (affectRowCount > 0) {
	    	// book_id : book_SQL.xml 에서 설정한 keyProperty
	        return map.get("book_id").toString();
	    }
	    
	    return null;
	}
	
	@Override
	public boolean edit(Map<String, Object> map) {
	    
		// 추가적으로 값을 가져올 필요 없으므로 하나의 값만 제대로 변경되었는지 여부를 boolean 으로 return
		int affectRowCount = this.bookDao.update(map);
	    
	    return affectRowCount == 1;	    
	}
	
	@Override
	public boolean remove(Map<String, Object> map) {
	    int affectRowCount = this.bookDao.delete(map);
	    
	    return affectRowCount == 1;	    
	}
	
}
