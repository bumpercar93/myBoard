package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

@WebServlet("/boardView")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private IBoardService boardService;
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
		postService = new PostServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageString = request.getParameter("page");
		String pageSizeString = request.getParameter("pageSize");
		
		int page = pageString == null ? 1 : Integer.parseInt(pageString);
		int pageSize = pageSizeString == null ? 10 : Integer.parseInt(pageSizeString);
		
		String id = request.getParameter("board_id");
		int board_id = Integer.parseInt(id);
		
		BoardVO boardVO = boardService.getBoard(board_id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("page", page);
		map.put("pageSize", pageSize);
		
		Map<String, Object> resultMap = postService.postPagingList(map);
		List<PostVO> postList = (List<PostVO>) resultMap.get("postList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		
		request.setAttribute("board_id", board_id);
		request.setAttribute("postList", postList);
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("page", page);
		request.setAttribute("pageSize", pageSize);
		request.getRequestDispatcher("/board/boardView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
