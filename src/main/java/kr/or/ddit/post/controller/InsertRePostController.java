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

import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.MyFileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/insertRePost")
@MultipartConfig(maxFileSize=1024*1024*3,maxRequestSize=1024*1024*15)
public class InsertRePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(InsertRePostController.class);
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("InsertRePostController doGet");
		int board_id = Integer.parseInt(request.getParameter("boardid"));
		int parent = Integer.parseInt(request.getParameter("postid"));
		
		request.setAttribute("myfileList", "");
		request.setAttribute("parent", parent);
		request.setAttribute("board_id", board_id);
		request.setAttribute("right", "rePost");
		request.getRequestDispatcher("/post/insertPostTest.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int post_parent = Integer.parseInt(request.getParameter("parent"));
		String post_title = request.getParameter("title");
		String post_content = request.getParameter("smarteditor");
		int board_id = Integer.parseInt(request.getParameter("boardid"));
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		String userId = userVO.getUserId();
		
		PostVO postVO = new PostVO(board_id, userId, post_parent, post_title, post_content);
		
		logger.debug("postVO : {}", postVO);
		
		int insertCnt = postService.insertPost(postVO);
		
		int post_id = postService.findMax();
		PostVO parentVO = postService.getPost(post_parent);
		logger.debug("parentVO : {}", parentVO);
		
		if(insertCnt > 0) {
			
			PostVO pVO = new PostVO(post_id, parentVO.getGroup_seq());
			postService.groupSeq(pVO);
			
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
