package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
		boardService = new BoardServiceImpl();
	}
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().setAttribute("BOARD_LIST", boardService.getAllBoard());
		
		if(request.getSession().getAttribute("USER_INFO") == null) {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LoginController doPost");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("password");
		
		UserVO userVO = userService.getUser(userId);
		pass = KISA_SHA256.encrypt(pass);
		
		if(userVO != null && userVO.getPass().equals(pass)) {
			request.getSession().setAttribute("USER_INFO", userVO);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}else {
			doGet(request, response);
		}
		
	}

}
