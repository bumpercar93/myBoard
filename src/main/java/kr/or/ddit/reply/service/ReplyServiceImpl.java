package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.dao.IReplyDao;
import kr.or.ddit.reply.dao.ReplyDaoImpl;
import kr.or.ddit.reply.model.ReplyVO;

public class ReplyServiceImpl implements IReplyService {
	private IReplyDao replyDao;
	
	public ReplyServiceImpl() {
		replyDao = new ReplyDaoImpl();
	}
	
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
		return replyDao.insertReply(replyVO);
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
		return replyDao.deleteReply(reply_id);
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
		return replyDao.getReplyList(post_id);
	}

}
