package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.myfile.model.MyfileVO;
import kr.or.ddit.myfile.service.IMyfileService;
import kr.or.ddit.myfile.service.MyfileServiceImpl;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/postInfo")
public class PostInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory
			.getLogger(PostInfoController.class);   
	private IPostService postService;
	private IMyfileService myfileService;
	private IReplyService replyService;
    
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		myfileService = new MyfileServiceImpl();
		replyService = new ReplyServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("PostInfoController doGet");
		String postid = request.getParameter("postid");
		int post_id = Integer.parseInt(postid);
		logger.debug("post_id : {}", post_id);
		
		String boardid = request.getParameter("boardid");
		logger.debug("boardid : {}", boardid);
		int board_id = Integer.parseInt(boardid);
		
		List<MyfileVO> myfileList = myfileService.getMyfile(post_id);
		
		PostVO postVO = postService.getPost(post_id);
		
		List<ReplyVO> replyList = replyService.getReplyList(post_id);
		
		request.setAttribute("board_id", board_id);
		request.setAttribute("replyList", replyList);
		request.setAttribute("postVO", postVO);
		request.getSession().setAttribute("myfileList", myfileList);
		request.getRequestDispatcher("/post/postInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		logger.debug("PostInfoController doPost");
		String reply_content = request.getParameter("reply");
		String postid = request.getParameter("postid");
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		logger.debug("reply_content : {}", reply_content);
		logger.debug("post_id : {}", postid);
		logger.debug("userVO : {}", userVO);
		int boardid = Integer.parseInt(request.getParameter("boardid"));
		
		int post_id = Integer.parseInt(postid);
		String userId = userVO.getUserId();
		
		ReplyVO replyVO = new ReplyVO(post_id, userId, reply_content);
		
		int insertCnt = replyService.insertReply(replyVO);
		
		if(insertCnt > 0) {
			logger.debug("ye");
			response.sendRedirect(request.getContextPath() + "/postInfo?postid=" +  post_id + "&boardid=" + boardid);
		}else {
			logger.debug("no");
			doGet(request, response);
		}
	}

}
