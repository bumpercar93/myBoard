package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao boardDao;
	
	
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}
	
	/**
	 * 
	* Method : getAllBoard
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 모든 게시판 정보 가져오기
	 */
	@Override
	public List<BoardVO> getAllBoard() {
		return boardDao.getAllBoard();
	}

	/**
	 * 
	* Method : insertBoard
	* 작성자 : PC06
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판 추가
	 */
	@Override
	public int insertBoard(BoardVO boardVO) {
		return boardDao.insertBoard(boardVO);
	}

	/**
	 * 
	* Method : updateBoard
	* 작성자 : PC06
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판 수정
	 */
	@Override
	public int updateBoard(BoardVO boardVO) {
		return boardDao.updateBoard(boardVO);
	}

	/**
	 * 
	* Method : getBoard
	* 작성자 : PC06
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 게시판 한개 정보 가져오기
	 */
	@Override
	public BoardVO getBoard(int board_id) {
		return boardDao.getBoard(board_id);
	}

}
