package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.mybatis.MyBatisUtil;

public class BoardDaoImpl implements IBoardDao {
	
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVO> boardList = sqlSession.selectList("board.getAllBoard");
		sqlSession.close();
		return boardList;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("board.insertBoard", boardVO);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("board.updateBoard", boardVO);
		sqlSession.commit();
		sqlSession.close();
		return updateCnt;
	}
	
	/**
	 * 
	* Method : getBoard
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 한개 정보 가져오기
	 */
	@Override
	public BoardVO getBoard(int board_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardVO boardVO = sqlSession.selectOne("board.getBoard", board_id);
		sqlSession.close();
		return boardVO;
	}

}
