package kr.or.ddit.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.reply.model.ReplyVO;

public class ReplyDaoImpl implements IReplyDao {

	/**
	 * 
	* Method : insertReply
	* 작성자 : PC06
	* 변경이력 :
	* @param replyVO
	* @return
	* Method 설명 : 댓글 등록
	 */
	@Override
	public int insertReply(ReplyVO replyVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("reply.insertReply", replyVO);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}

	/**
	 * 
	* Method : deleteReply
	* 작성자 : PC06
	* 변경이력 :
	* @param reply_id
	* @return
	* Method 설명 : 댓글 삭제(구분만 바꿈, 결국 update구문)
	 */
	@Override
	public int deleteReply(int reply_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("reply.deleteReply", reply_id);
		sqlSession.commit();
		sqlSession.close();
		return updateCnt;
	}

	/**
	 * 
	* Method : getReplyList
	* 작성자 : PC06
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 한 게시글에 해당하는 댓글리스트 가져오기
	 */
	@Override
	public List<ReplyVO> getReplyList(int post_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<ReplyVO> replyList = sqlSession.selectList("reply.getReplyList", post_id);
		sqlSession.close();
		return replyList;
	}

}
