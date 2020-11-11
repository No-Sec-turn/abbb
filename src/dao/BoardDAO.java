package dao;

import java.sql.Connection;

import vo.BoardBean;

public class BoardDAO {
	
	/*
	 * 싱글톤 디자인 패턴을 활용한 BoardDAO 인스턴스 작업
	 * 
	 */
	
	
	
	private BoardDAO() {}
	
	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	public static void setInstance(BoardDAO instance) {
		BoardDAO.instance = instance;
	}
		
	//================================================================
	
	Connection con; // Connection 객체를 전달받아 저장할 멤버변수
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	//글등록 작업 
	public int insertArticle(BoardBean boardBean) {
		System.out.println("BoardDAO - insertArticle()");
		int insertCount = 1;
		return insertCount;
	}
	
	
	
	
	// 외부(Service 클래스)로부터 Connection 객체를 전달받아
	// 멤버변수에 저장하는 setConnection() 메서드 정의
	
}
