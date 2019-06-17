package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.user.dao.UserDaoImpl;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDaoImplTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(BoardDaoImplTest.class);
	
	private IBoardDao boardDao;
	
	@Before
	public void setup() {
		boardDao = new BoardDaoImpl();
	}
	
	@Test
	public void getAllBoardTest() {
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList = boardDao.getAllBoard();
		logger.debug("boardList : {}", boardList.get(1).getBoard_dt());
		/***Then***/
		assertEquals(2, boardList.size());
	}
	
	@Test
	public void insertBoardTest() {
		/***Given***/
		BoardVO boardVO = new BoardVO("admin", "테스트", "Y");
		
		/***When***/
		int insertCnt = boardDao.insertBoard(boardVO);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateBoardTest() {
		/***Given***/
		BoardVO boardVO = new BoardVO(2, "테스트테스트", "Y");

		/***When***/
		int updateCnt = boardDao.updateBoard(boardVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void getBoardTest() {
		/***Given***/
		int board_id = 1;

		/***When***/
		BoardVO boardVO = boardDao.getBoard(board_id);
		
		/***Then***/
		logger.debug("boardVO : {}", boardVO);
	}

}
