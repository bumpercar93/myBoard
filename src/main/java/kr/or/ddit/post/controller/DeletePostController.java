package kr.or.ddit.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/deletePost")
public class DeletePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory
			.getLogger(DeletePostController.class);   
    private IPostService postService;
    
    @Override
    public void init() throws ServletException {
    	postService = new PostServiceImpl();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postid = request.getParameter("postid");
		logger.debug("postid : {}", postid);
		String boardid = request.getParameter("boardid");
		logger.debug("boardid : {}", boardid);
		
		int board_id = Integer.parseInt(boardid);
		int post_id = Integer.parseInt(postid);
		int cnt = postService.deletePost(post_id);
		if(cnt > 0) {
			response.sendRedirect(request.getContextPath() + "/boardView?board_id=" + board_id);
		}
	}

}
