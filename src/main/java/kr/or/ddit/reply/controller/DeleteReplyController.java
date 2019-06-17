package kr.or.ddit.reply.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/deleteReply")
public class DeleteReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory
			.getLogger(DeleteReplyController.class);  
    private IReplyService replyService;
    
    @Override
    public void init() throws ServletException {
    	replyService = new ReplyServiceImpl();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String replyid = request.getParameter("replyid");
		String postid = request.getParameter("postid");
		int boardid = Integer.parseInt(request.getParameter("boardid"));
		logger.debug("replyid : {}", replyid);
		logger.debug("postid : {}", postid);
		
		int reply_id = Integer.parseInt(replyid);
		int post_id = Integer.parseInt(postid);
		
		int deleteCnt = replyService.deleteReply(reply_id);
		if(deleteCnt > 0) {
			response.sendRedirect(request.getContextPath() + "/postInfo?postid=" + post_id + "&boardid=" + boardid);
		}else {
			request.getRequestDispatcher("/posfInfopostid=" + post_id + "&boardid=" + boardid).forward(request, response);
		}
	}

}
