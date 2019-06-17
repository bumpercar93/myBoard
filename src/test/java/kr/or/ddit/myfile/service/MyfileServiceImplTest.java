package kr.or.ddit.myfile.service;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.myfile.model.MyfileVO;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyfileServiceImplTest {

	private IMyfileService myfileService;
	
	@Before
	public void setup() {
		myfileService = new MyfileServiceImpl();
	}
	
	@Test
	public void insertMyfileTest() {
		/***Given***/
		MyfileVO myfileVO = new MyfileVO(2, "test", "test.jsp");
		/***When***/
		int insertCnt = myfileService.insertMyfile(myfileVO);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void getMyfileTest() {
		/***Given***/
		int post_id = 24;
		/***When***/
		List<MyfileVO> myfileList = myfileService.getMyfile(post_id);
		/***Then***/
		assertEquals(5, myfileList.size());
	}

}
