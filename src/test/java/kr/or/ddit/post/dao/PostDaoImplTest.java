package kr.or.ddit.post.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.PostVO;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostDaoImplTest {
	
	private IPostDao postDao;
	private static final Logger logger = LoggerFactory
			.getLogger(PostDaoImplTest.class);
	
	@Before
	public void setup() {
		postDao = new PostDaoImpl();
	}
	
	@Test
	public void getAllPostTest() {
		/***Given***/
		int board_id = 1;
		/***When***/
		List<PostVO> postList = postDao.getAllPost(board_id);
		logger.debug("postList.get(0) : {}", postList.get(0));
		/***Then***/
		assertEquals(2, postList.size());
	}
	
	@Test
	public void insertPostTest() {
		/***Given***/
		PostVO postVO = new PostVO(1, "admin", 0, "그룹시퀀스 테스트", "그룹 시퀀스 테스트");
		/***When***/
		postDao.insertPost(postVO);
		int post_id = postDao.findMax();
		PostVO pVO = new PostVO(post_id, post_id);
		int cnt = postDao.groupSeq(pVO);
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void updatePostTest() {
		/***Given***/
 		PostVO postVO = new PostVO(22, "테스트", "테스트", "Y");
		/***When***/
		int updatrCnt = postDao.updatePost(postVO);
		/***Then***/
		assertEquals(1, updatrCnt);
	}
	
	@Test
	public void getPostTest() {
		/***Given***/
		int post_id = 2;
		/***When***/
		PostVO postVO = postDao.getPost(post_id);
		/***Then***/
		logger.debug("postVO : {}", postVO);
	}
	
	@Test
	public void postPagingListTest() {
		/***Given***/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", 1);
		map.put("page", 1);
		map.put("pageSize", 10);
		/***When***/
		List<PostVO> postPagingList = postDao.postPagingList(map);
		/***Then***/
		for(PostVO postVO : postPagingList) {
			logger.debug("postVO : {}", postVO);
		}
		assertEquals(10, postPagingList.size());
	}
	
	@Test
	public void postCntTest() {
		/***Given***/
		int board_id = 1;
		/***When***/
		int postCnt = postDao.postCnt(board_id);
		/***Then***/
		assertEquals(12, postCnt);
	}
	
	@Test
	public void findMaxTest() {
		/***Given***/

		/***When***/
		int max = postDao.findMax();
		/***Then***/
		assertEquals(19, max);
	}
	
}
