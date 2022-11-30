package post;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import post.PostDAO;
import post.PostDTO;

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		MultipartRequest multi = null;
		int fileMaxSize = 10 * 1024 * 1024;
		String savePath = request.getRealPath("/upload").replaceAll("\\\\","/");
		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		}catch(Exception e) {
			request.getSession().setAttribute("messageType","오류 메시지");
			request.getSession().setAttribute("mewssageContent", "파일 크기는 10MB를 넘을 수 없습니다.");
			/* response.sendRedirect("post_to_student_council_Write.jsp"); */
			return;
		}
		HttpSession session = request.getSession();
		String userID = multi.getParameter("userID");
		String postTitle = multi.getParameter("postTitle");
		String postContent = multi.getParameter("postContent");
		String postDivide = "";
		int boardID=Integer.parseInt(multi.getParameter("boardID"));
		
		if(postTitle==null || postContent==null||postTitle.equals("")||postContent.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "내용을 모두 채워주세요.");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		File file = multi.getFile("postFile");
		PostDAO postDAO = new PostDAO();
		String postFile = "";
		String postRealFile = "";
		
		if(file!=null) {
			postFile = multi.getOriginalFileName("postFile");
			postRealFile = file.getName();
		}
		postDAO.write(new PostDTO(0, 0,userID,"", postTitle,postContent,postDivide,postFile,postRealFile,0,0,0,0,0,0),boardID);

		session.setAttribute("messageType", "성공 메시지");
		session.setAttribute("messageContent", "게시글 작성이 완료되었습니다.");
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("history.go(-2);");
		script.println("</script>");
		script.close();
		return;
	}
}
