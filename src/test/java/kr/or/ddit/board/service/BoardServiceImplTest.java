package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.model.BoardVO;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceImplTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(BoardServiceImplTest.class);
	
	private IBoardService boardService;
	
	@Before
	public void setup() {
		boardService = new BoardServiceImpl();
	}

	@Test
	public void getAllBoardTest() {
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList = boardService.getAllBoard();
		logger.debug("boardList : {}", boardList.get(1).getBoard_dt());
		/***Then***/
		assertEquals(2, boardList.size());
	}
	
	@Test
	public void insertBoardTest() {
		/***Given***/
		BoardVO boardVO = new BoardVO("admin", "테스트", "Y");
		
		/***When***/
		int insertCnt = boardService.insertBoard(boardVO);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateBoardTest() {
		/***Given***/
		BoardVO boardVO = new BoardVO(6, "Q&A테스트", "N");

		/***When***/
		int updateCnt = boardService.updateBoard(boardVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void getBoardTest() {
		/***Given***/
		int board_id = 1;

		/***When***/
		BoardVO boardVO = boardService.getBoard(board_id);
		
		/***Then***/
		logger.debug("boardVO : {}", boardVO);
	}

}
