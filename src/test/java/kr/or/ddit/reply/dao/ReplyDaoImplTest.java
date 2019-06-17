package kr.or.ddit.reply.dao;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVO;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReplyDaoImplTest {

	private IReplyDao replyDao;
	
	@Before
	public void setup() {
		replyDao = new ReplyDaoImpl();
	}
	
	@Test
	public void insertReplyTest() {
		/***Given***/
		ReplyVO replyVO = new ReplyVO(26, "moon", "ㅇㅅㅇ");
		/***When***/
		int insertCnt = replyDao.insertReply(replyVO);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteReplyTest() {
		/***Given***/
		int reply_id = 9;
		/***When***/
		int cnt = replyDao.deleteReply(reply_id);
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void getReplyListTest() {
		/***Given***/
		int post_id = 26;
		/***When***/
		List<ReplyVO> replyList = replyDao.getReplyList(post_id);
		/***Then***/
		assertEquals(2, replyList.size());
	}

}
