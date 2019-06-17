package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.myfile.model.MyfileVO;
import kr.or.ddit.myfile.service.IMyfileService;
import kr.or.ddit.myfile.service.MyfileServiceImpl;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.MyFileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/insertPost")
@MultipartConfig(maxFileSize=1024*1024*3,maxRequestSize=1024*1024*15)
public class InsertPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory
			.getLogger(InsertPostController.class);
	
	private IPostService postService;
	private IMyfileService myfileService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		myfileService = new MyfileServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_id = 0;
		if(request.getParameter("board_id") == null) { // 수정
			board_id = Integer.parseInt(request.getParameter("boardid"));
			int post_id = Integer.parseInt(request.getParameter("postid"));
			String post_title = request.getParameter("post_title");
			String post_content = request.getParameter("post_content");
			logger.debug("board_id : {}", board_id);
			List<MyfileVO> myfileList = myfileService.getMyfile(post_id);
			request.setAttribute("myfileList", myfileList);
			request.setAttribute("post_id", post_id);
			request.setAttribute("post_title", post_title);
			request.setAttribute("post_content", post_content);
			request.setAttribute("right", "update");
		}else {
			board_id = Integer.parseInt(request.getParameter("board_id"));
		}
		
		request.setAttribute("board_id", board_id);
		request.getRequestDispatcher("/post/insertPostTest.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String post_title = request.getParameter("title");
		String post_content = request.getParameter("smarteditor");
		int board_id = Integer.parseInt(request.getParameter("boardid"));
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		String userId = userVO.getUserId();
		
		PostVO postVO = new PostVO(board_id, userId, 0, post_title, post_content);
		
		logger.debug("postVO : {}", postVO);
		
		int insertCnt = postService.insertPost(postVO);
		
		int post_id = postService.findMax();
		if(insertCnt > 0) {
			
			PostVO pVO = new PostVO(post_id, post_id);
			postService.groupSeq(pVO);
			
			Collection<Part> parts = request.getParts();
			for(Part part : parts) {
				if("myfile".equals(part.getName()) && part.getSize() > 0) {
					MyFileUtil.fileUpload(part, post_id);
				}
			}
			
			response.sendRedirect(request.getContextPath() + "/postInfo?boardid=" + board_id + "&postid=" + post_id);
		}
		
		
	}//doPost

}
