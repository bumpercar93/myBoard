package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.myfile.service.IMyfileService;
import kr.or.ddit.myfile.service.MyfileServiceImpl;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.util.MyFileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/updatePost")
@MultipartConfig(maxFileSize=1024*1024*3,maxRequestSize=1024*1024*15)
public class UpdatePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory
			.getLogger(UpdatePostController.class);
    private IPostService postService;
    private IMyfileService myfileService;
    
    @Override
    public void init() throws ServletException {
    	postService = new PostServiceImpl();
    	myfileService = new MyfileServiceImpl();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("UpdatePostController doPost");
		
		request.setCharacterEncoding("utf-8");
		
		logger.debug("boardid : {}", request.getParameter("boardid"));
		
//		String[] fileIds = request.getParameterValues("file_id");
//		for(String fileId : fileIds) {
//			logger.debug("fileId : {}", fileId);
//			if(fileId.equals("")) {
//				logger.debug("화이트!");
//			}
//		}
		
		String post_title = request.getParameter("title");
		String post_content = request.getParameter("smarteditor");
		int board_id = Integer.parseInt(request.getParameter("boardid"));
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		PostVO postVO = new PostVO(post_id, post_title, post_content, "Y");
		
		int cnt = postService.updatePost(postVO);
		if(cnt > 0) {
			
			String[] fileIds = request.getParameterValues("file_id");
			
			for(String fileId : fileIds) {
				if(fileId.equals("")) continue;
				int myfile_id = Integer.parseInt(fileId);
				myfileService.deleteFile(myfile_id);
			}
			
			Collection<Part> parts = request.getParts();
			for(Part part : parts) {
				if("myfile".equals(part.getName()) && part.getSize() > 0) {
					MyFileUtil.fileUpload(part, post_id);
				}
			}
			
			response.sendRedirect(request.getContextPath() + "/postInfo?boardid=" + board_id + "&postid=" + post_id);
		
		}
		
	}

}
