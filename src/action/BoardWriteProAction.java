package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Set;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardWriteProAction implements Action {


	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardWriteProAction! 연결 잘 됨" );
		
		ActionForward forward = null;
		
		// MultiPartRequest 객체를 가져와서
		// 전달받은 파라미터(글쓴이, 비밀번호, 글 제목, 글 내용, 작성자) 가져오기
		
		//현재 컨텍스트(객체) 정보 가져오기 위해
		// request 객체의 getServletContext() 메서드를 호출
		ServletContext context = request.getServletContext();
		
		// 프로젝트 상에서 설정한 가상 업로드 폴더 경로 지정
		// 현재 루트 위치가 webcontent 폴더이므로 하위 폴더를 "/하위폴더명" 지정
		String saveFolder = "/boardUpload";
		
		// 가상 폴더에 대응하는 실제 폴더 위치를 가져오기 위해
		// ServletContext 객체 getRealPath() 메서드를 호출
		// => 파라미터 : 가상 업로드 폴더 경로
		String realFolder = context.getRealPath(saveFolder);
		System.out.println("실제 업로드 폴더 : " + realFolder);
		// 워크스페이스 명: \.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC_Board\boardUpload
		//	실제 업로드 위치:
		//  ->  D:\JSP_Model2\workspace_jsp_model2\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC_Board\boardUpload
		
		int fileSize = 2 * 1024 * 1024 ; // 2MB (
		 
		
		
		// 뷰페이지 (JSP) 에서 전달된 파라미터 들이
		// enctype="multipart/form-data" 타입으로 전달될 경우
		// 일반 request 객체가 아닌 MultipartRequest 객체를 통해 전달받아야 하므로
		// MultipartRequest 객체 생성 필수!
		
		MultipartRequest multi = new MultipartRequest(
				
				request, // HttpServletRequest(request)객체 
				realFolder, // 실제 업로드 폴더 
				fileSize,   //  한 번에 업로드 가능한 1개 파일 최대 크기 
				"UTF-8",    // 한글파일 처리를 위한 인코딩 방식 지정 
				new DefaultFileRenamePolicy()  //파일 명 중복에 대한 처리를 담당하는 객체
				);
		
//		String board_name = multi.getParameter("BOARD_NAME");
//		String board_pass = multi.getParameter("BOARD_PASS");
//		String board_subject = multi.getParameter("BOARD_SUBJECT");
//		String board_content = multi.getParameter("BOARD_CONTENT");
//		String board_file = (String)multi.getFileNames().nextElement();
//		String board_file = multi.getFilesystemName(arg0)
		
		//원본이름과 실제 다운로드 파일 이름을 DB에 각각 따로 저장 해줘야한다!
		

		// => 주로 화면에 표시할 파일 명으로 사용
//		System.out.println("getOriginal filename() : " +  multi.getOriginalFileName("board_file"));
		
		//중복 처리까지 완료된 실제 파일 명
		// => 주로, 다운로드 시 실제 다운로드 링크로 사용할 파일명으로 사용 
//		System.out.println("getFilesystemName()  : " +  multi.getFilesystemName("board_file"));

//		String board_file = multi.getOriginalFileName("BOARD_FILE");
		
//		System.out.println("글쓴이 :  " + board_name);
//		System.out.println("비번 : " + board_pass);
//		System.out.println("글제목 : " + board_subject);
//		System.out.println("글내용 : " + board_content);
//		System.out.println("파일명 : " + board_file);		
// ------------------------------------------------------------------------------------------------------------------------------
		
		BoardBean boardBean = new BoardBean();
//		boardBean.setBoard_name(multi.getParameter("BOARD_NAME"));
//		boardBean.setBoard_pass(multi.getParameter("BOARD_PASS"));
//		boardBean.setBoard_subject(multi.getParameter("BOARD_SUBJECT"));
//		boardBean.setBoard_content(multi.getParameter("BOARD_CONTENT"));
//		boardBean.setBoard_file(multi.getOriginalFileName("BOARD_FILE")); //파일명 가져오는 방법은 주의!
//		
		
		
		
// ------------------------------------------------------------------------------------------------------------------------------
		// 서비스 클래스를 통해 실제 글 등록 작업 수행을 위한 요청
		BoardWriteProService service = new BoardWriteProService();
		
		boolean isWriteSuccess = service.registArticle(boardBean);
		
		if(!isWriteSuccess) {  // iswirteSuccess가 false일 때 실행
			
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('글 등록 실패')");
			out.println("history.back"); // 또는 out.println("history.go(-1)");
			out.println("</script>");
			
			
		} else {
			
			// 1.
			forward = new ActionForward();
			
			// 2.     
			// redirect 방식일 때 "/"를 붙이면 URL에 프로젝트 명까지 사라짐
			forward.setPath("BoardList.bo");
			
			// 3.
			forward.setRedirect(true);
			
			//4.
			
			
			
		}
		
		
		return forward;   //BoardFrontController 로 이동
		
		
		

		
		
		
		
		
		
		
		
		
	
	}
	

}
