package page;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class PageImageServlet extends HttpServlet {
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
			response.sendRedirect("admin_customizing.jsp");
			return;
		}
		String fileName="";
		File file = multi.getFile("pageImage");
		HttpSession session = request.getSession();
		if(file !=null) {
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
			if(ext.equals("ico")) {
				String prev = new PageDAO().searchPageImage();
				File prevFile = new File(savePath + "/" + prev);
				if(prevFile.exists()) {
					prevFile.delete();
				}
				fileName = file.getName();
			}else {
				if(file.exists()) {
					file.delete();
				}
				session.setAttribute("messageType", "오류 메시지");
				session.setAttribute("messageContent", "아이콘 파일만 업로드 가능합니다.");
				response.sendRedirect("admin_customizing.jsp");
				return;
			}
		}
		new PageDAO().ImageUpdate(fileName);
		session.setAttribute("messageType", "성공 메시지");
		session.setAttribute("messageContent", "성공적으로 대표이미지를 변경하였습니다.");
		response.sendRedirect("index.jsp");
		return;
	}

}
