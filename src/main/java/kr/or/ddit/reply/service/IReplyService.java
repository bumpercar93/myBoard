package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVO;

public interface IReplyService {

	/**
	 * 
	* Method : insertReply
	* 작성자 : PC06
	* 변경이력 :
	* @param replyVO
	* @return
	* Method 설명 : 댓글 등록
	 */
	int insertReply(ReplyVO replyVO);

	/**
	 * 
	* Method : deleteReply
	* 작성자 : PC06
	* 변경이력 :
	* @param reply_id
	* @return
	* Method 설명 : 댓글 삭제(구분만 바꿈, 결국 update구문)
	 */
	int deleteReply(int reply_id);

	/**
	 * 
	* Method : getReplyList
	* 작성자 : PC06
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 한 게시글에 해당하는 댓글리스트 가져오기
	 */
	List<ReplyVO> getReplyList(int post_id);

}
