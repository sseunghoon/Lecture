package user;

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


@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
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
			request.getSession().setAttribute("messageType","���� �޽���");
			request.getSession().setAttribute("mewssageContent", "���� ũ��� 10MB�� ���� �� �����ϴ�.");
			response.sendRedirect("profileUpdate.jsp");
			return;
		}
		String userID = multi.getParameter("userID");
		HttpSession session = request.getSession();
		if(!userID.equals((String) session.getAttribute("userID"))){
			session.setAttribute("messageType", "���� �޽���");
			session.setAttribute("messageContent", "������ �� �����ϴ�.");
			response.sendRedirect("index.jsp");
			return;
		}
		String fileName="";
		File file = multi.getFile("userProfile");
		if(file !=null) {
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
			if(ext.equals("jpg") ||ext.equals("png") ||ext.equals("gif")) {
				String prev = new UserDAO().searchProfileByID(userID);
				File prevFile = new File(savePath + "/" + prev);
				if(prevFile.exists()) {
					prevFile.delete();
				}
				fileName = file.getName();
			}else {
				if(file.exists()) {
					file.delete();
				}
				session.setAttribute("messageType", "���� �޽���");
				session.setAttribute("messageContent", "�̹��� ���ϸ� ���ε� �����մϴ�.");
				response.sendRedirect("profileUpdate.jsp");
				return;
			}
			new UserDAO().profile(userID, fileName);
		}
		String userEmail = multi.getParameter("userEmail");
		String userName = multi.getParameter("userName");
		String aboutMe = multi.getParameter("aboutMe");
		
		new UserDAO().update(userID, userName, userEmail,aboutMe);
		
		session.setAttribute("messageType", "���� �޽���");
		session.setAttribute("messageContent", "���������� �������� ������Ʈ �Ͽ����ϴ�.");
		response.sendRedirect("myPage.jsp");
		return;
	}

}
