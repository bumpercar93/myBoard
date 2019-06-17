package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/boardSetting")
public class BoardSettingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(BoardSettingController.class);
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("BoardSettingController doGet");
		List<BoardVO> boardList = boardService.getAllBoard();
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("/board/boardSetting.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("BoardSettingController doPost");
		request.setCharacterEncoding("utf-8");
		
		String right = request.getParameter("insertOrModify");
		if(right.equals("insert")) {
			
			String board_name = request.getParameter("insertBoardTxt");
			String board_use = request.getParameter("selectBox");
			UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
			String userId = userVO.getUserId();
			logger.debug("board_name : {}", board_name);
			logger.debug("board_use : {}", board_use);
			logger.debug("userId : {}", userId);
			
			BoardVO boardVO = new BoardVO(userId, board_name, board_use);
			
			int insertCnt = boardService.insertBoard(boardVO);
			if(insertCnt > 0) {
				getServletContext().setAttribute("BOARD_LIST", boardService.getAllBoard());
				response.sendRedirect(request.getContextPath() + "/boardSetting");
			}else {
				doGet(request, response);
			}
			
		}else {
			
			String board_name = request.getParameter("modifyName");
			String board_use = request.getParameter("modifyUse");
			String id = request.getParameter("modifyId");
			int board_id = Integer.parseInt(id);
			logger.debug("board_name : {}", board_name);
			logger.debug("board_use : {}", board_use);
			logger.debug("board_id : {}", board_id);
			
			BoardVO boardVO = new BoardVO(board_id, board_name, board_use);
			int updateCnt = boardService.updateBoard(boardVO);
			if(updateCnt > 0) {
				getServletContext().setAttribute("BOARD_LIST", boardService.getAllBoard());
				response.sendRedirect(request.getContextPath() + "/boardSetting");
			}else {
				doGet(request, response);
			}
			
		}
		
		
	}

}
