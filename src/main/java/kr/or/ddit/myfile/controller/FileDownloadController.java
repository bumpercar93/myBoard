package kr.or.ddit.myfile.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.myfile.model.MyfileVO;
import kr.or.ddit.myfile.service.IMyfileService;
import kr.or.ddit.myfile.service.MyfileServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/fileDownload")
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory
			.getLogger(FileDownloadController.class);
    private IMyfileService myfileService;
    
    @Override
    public void init() throws ServletException {
    	myfileService = new MyfileServiceImpl();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("FileDownloadController doGet");
		int myfile_id = Integer.parseInt(request.getParameter("myfile_id"));
		MyfileVO myfileVO = myfileService.getFile(myfile_id);
		
		String temp = myfileVO.getMyfile_path();
		int index = temp.lastIndexOf("\\");
		String path = temp.substring(0, index+1);
		String fileName = temp.substring(index+1);
		String viewFileName = myfileVO.getMyfile_name();
		logger.debug("path : {}", path);
		logger.debug("fileName : {}", fileName);
		
		request.setAttribute("path", path);
		request.setAttribute("fileName", fileName);
		request.setAttribute("viewFileName", viewFileName);
		
		request.getRequestDispatcher("post/down.jsp").forward(request, response);
	}

}
