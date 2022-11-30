package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downloadAction")
public class downloadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String fileName=request.getParameter("file");
		
		String directory=this.getServletContext().getRealPath("/upload/");
		File file=new File(directory +"/"+fileName);
		
		String mimeType=getServletContext().getMimeType(file.toString());
		if(mimeType==null) {
			response.setContentType("application/octet-stream");
		}
		
		String downloadName=null;
		if(request.getHeader("user-agent").indexOf("MSIE")==-1) { //인터넷 익스플로어로 접속한 사람이 아닐 경우
			downloadName=new String(fileName.getBytes("UTF-8"),"8859_1");
		}else { //인터넷 익스플로어로 접속한 사람일 경우 
			downloadName=new String(fileName.getBytes("EUC-KR"),"8859_1");
		}
		
		response.setHeader("Content-Disposition","attachment;filename=\""
				+downloadName+"\";");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		byte b[]=new byte[1024];
		int data=0;
		
		while((data=(fileInputStream.read(b,0,b.length)))!=-1) {
			servletOutputStream.write(b,0,data);
		}
		
		new FileDAO().hit(fileName);
		
		servletOutputStream.flush(); // 남아있는 데이터들을 전부 다 보내게 함 
		servletOutputStream.close();
		fileInputStream.close();
	}

}
