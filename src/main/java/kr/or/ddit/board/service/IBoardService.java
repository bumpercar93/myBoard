package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {
	
	/**
	 * 
	* Method : getAllBoard
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 모든 게시판 정보 가져오기
	 */
	List<BoardVO> getAllBoard();
	
	/**
	 * 
	* Method : insertBoard
	* 작성자 : PC06
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판 추가
	 */
	int insertBoard(BoardVO boardVO);

	/**
	 * 
	* Method : updateBoard
	* 작성자 : PC06
	* 변경이력 :
	* @param boardVO
	* @return
	* Method 설명 : 게시판 수정
	 */
	int updateBoard(BoardVO boardVO);

	/**
	 * 
	* Method : getBoard
	* 작성자 : PC06
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 게시판 한개 정보 가져오기
	 */
	BoardVO getBoard(int board_id);

}
