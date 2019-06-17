package kr.or.ddit.myfile.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.myfile.model.MyfileVO;
import kr.or.ddit.myfile.service.IMyfileService;
import kr.or.ddit.myfile.service.MyfileServiceImpl;

@WebServlet("/fileDelete")
@MultipartConfig(maxFileSize=1024*1024*3,maxRequestSize=1024*1024*15)
public class FileDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IMyfileService myfileService;
	private static final Logger logger = LoggerFactory
			.getLogger(FileDeleteController.class);
	
	@Override
	public void init() throws ServletException {
		myfileService = new MyfileServiceImpl();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int myfile_id = Integer.parseInt(request.getParameter("file_id"));
		int deleteCnt = myfileService.deleteFile(myfile_id);
		if(deleteCnt > 0) {
			int board_id = Integer.parseInt(request.getParameter("boardid"));
			int post_id = Integer.parseInt(request.getParameter("post_id"));
			String post_title = request.getParameter("title");
			String post_content = request.getParameter("smarteditor");
			logger.debug("board_id : {}", board_id);
			List<MyfileVO> myfileList = myfileService.getMyfile(post_id);
			request.setAttribute("myfileList", myfileList);
			request.setAttribute("post_id", post_id);
			request.setAttribute("post_title", post_title);
			request.setAttribute("post_content", post_content);
			request.setAttribute("right", "update");
			request.setAttribute("board_id", board_id);
			request.getRequestDispatcher("/post/insertPostTest.jsp").forward(request, response);
		}
	}

}
