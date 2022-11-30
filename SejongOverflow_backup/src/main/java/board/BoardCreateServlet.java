package board;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDAO;
import board.BoardDTO;

public class BoardCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		String boardTitle = null;
		if(request.getParameter("boardTitle")!=null) {
			boardTitle=request.getParameter("boardTitle");
		}
		String boardURL = null;
		if(request.getParameter("boardURL")!=null) {
			boardURL=request.getParameter("boardURL");
		}
		int boardID = 0;
		if(request.getParameter("boardID")!=null) {
			boardID=Integer.parseInt(request.getParameter("boardID"));
		}
		int boardLevel = -1;
		if(request.getParameter("boardLevel")!=null) {
			boardLevel=Integer.parseInt(request.getParameter("boardLevel"));
		}
		if(boardTitle==null ||boardTitle.equals("")||boardURL==null ||boardURL.equals("")||boardID==0||boardLevel==-1) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "빈칸을 모두 채워주세요.");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다.')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.create(new BoardDTO(boardID,boardTitle,boardURL,1,boardLevel));

		session.setAttribute("messageType", "성공 메시지");
		session.setAttribute("messageContent", "게시판 개설이 완료되었습니다.");
		response.sendRedirect("admin_board.jsp");
		return;
	}
}
