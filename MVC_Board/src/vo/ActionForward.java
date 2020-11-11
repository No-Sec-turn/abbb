package vo;

public class ActionForward {

	/*
	 * 서블릿에서 클라이언트로부터 요청을 받아 처리 후
	 * View 페이지 등으로 포워딩 할때
	 * 이동할 View 페이지의 URL(주소)과
	 * 포워딩 방식(Redirect or Dispatcher)을 다루기 위한 클래스
	 * 
	 */
	
	private String path; 
	private boolean redirect;  // 변수에 is안들어도 됨 // 멤버 변수를 직접 건드릴 일 없이 get/set메서드로 처리
	// treu : Redirect 방식. flase : Dispatcher 방식
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {   // 자동으로 isRedircet 메서드가 생성됨.. boolean 타입이면 자동으로 생성
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	

	
}
