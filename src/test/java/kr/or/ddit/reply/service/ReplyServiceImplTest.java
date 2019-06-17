package kr.or.ddit.reply.service;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVO;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReplyServiceImplTest {
	
	private IReplyService replyService;
	
	@Before
	public void setup() {
		replyService = new ReplyServiceImpl();
	}

	@Test
	public void insertReplyTest() {
		/***Given***/
		ReplyVO replyVO = new ReplyVO(26, "brown", "빡시다");
		/***When***/
		int insertCnt = replyService.insertReply(replyVO);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteReplyTest() {
		/***Given***/
		int reply_id = 9;
		/***When***/
		int cnt = replyService.deleteReply(reply_id);
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void getReplyListTest() {
		/***Given***/
		int post_id = 26;
		/***When***/
		List<ReplyVO> replyList = replyService.getReplyList(post_id);
		/***Then***/
		assertEquals(3, replyList.size());
	}

}
