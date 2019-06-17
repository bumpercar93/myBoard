package kr.or.ddit.post.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.PostVO;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostServiceImplTest {

	private IPostService postService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(PostServiceImplTest.class);
	
	@Before
	public void setup() {
		postService = new PostServiceImpl();
	}
	
	@Test
	public void getAllPostTest() {
		/***Given***/
		int board_id = 1;
		/***When***/
		List<PostVO> postList = postService.getAllPost(board_id);
		logger.debug("postList.get(0) : {}", postList.get(0));
		/***Then***/
		assertEquals(2, postList.size());
	}
	
	@Test
	public void insertPostTest() {
		/***Given***/
		PostVO postVO = new PostVO(1, "brown", 2, "테스트2", "테스트2");
		/***When***/
		int insertCnt = postService.insertPost(postVO);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updatePostTest() {
		/***Given***/
 		PostVO postVO = new PostVO(7, "테스트1", "테스트1", "Y");
		/***When***/
		int updatrCnt = postService.updatePost(postVO);
		/***Then***/
		assertEquals(1, updatrCnt);
	}
	
	@Test
	public void getPostTest() {
		/***Given***/
		int post_id = 2;
		/***When***/
		PostVO postVO = postService.getPost(post_id);
		/***Then***/
		logger.debug("postVO : {}", postVO);
	}
}
