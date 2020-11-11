package svc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sun.org.apache.xml.internal.security.utils.JavaUtils;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

// Action 클래스로부터 요청받은 자겁에 대한 데이터 등을 전달 받아
// Model(DAO 클래스)을 통해 실제 작업 처리를 요청하고
// 처리 결과를 리턴 받아 해당 결과에 대한 판별을 통해
// 결과값으로 처리할 데이터를 리턴
public class BoardWriteProService {

	//글 쓰기(등록) 요청을 처리하기 위한 registArticle() 메서드 정의
	// => 파라미터 : 게시물 정보(BoardBean)
	// => 리턴 타입: boolean(isWriteSuccess)
	
	public boolean registArticle(BoardBean boardBean) {
		
		System.out.println("BoardWriteProService! - registArticle() ");
		
		boolean isWriteSuccess = false; // 글 등록 성공 여부를 저장하는 변수명
		
		//1. (공통)
		Connection con = JdbcUtil.getConnection();
		
		//2. (공통)
		BoardDAO boardDAO = BoardDAO.getInstance();
		
		//3. (공통)
		
		boardDAO.setConnection(con);
		System.out.println("1");
		//4. 
		int insertCount = boardDAO.insertArticle(boardBean);
				
		
		//5.
		if(insertCount >0) {
			JdbcUtil.commit(con);
			isWriteSuccess = true;
		}else {
			JdbcUtil.rollback(con);
		}
		System.out.println("2");
		//6.
		JdbcUtil.close(con);
		
		
		//7. 
		return isWriteSuccess; //BoardwriteProAction로 이동
		
		
		
 
		
	}

	
	
}
