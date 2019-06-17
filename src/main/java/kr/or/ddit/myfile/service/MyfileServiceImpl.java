package kr.or.ddit.myfile.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.myfile.dao.IMyfileDao;
import kr.or.ddit.myfile.dao.MyfileDaoImpl;
import kr.or.ddit.myfile.model.MyfileVO;
import kr.or.ddit.util.MyFileUtil;

public class MyfileServiceImpl implements IMyfileService {
	
	private IMyfileDao myfileDao;
	private static final Logger logger = LoggerFactory
			.getLogger(MyfileServiceImpl.class);
	
	public MyfileServiceImpl() {
		myfileDao = new MyfileDaoImpl();
	}
	
	/**
	 * 
	* Method : insertMyfile
	* 작성자 : PC06
	* 변경이력 :
	* @param myfileVO
	* @return
	* Method 설명 : 첨부파일 등록
	 */
	@Override
	public int insertMyfile(MyfileVO myfileVO) {
		return myfileDao.insertMyfile(myfileVO);
	}

	/**
	 * 
	* Method : getMyfile
	* 작성자 : PC06
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 1개의 게시물에 해당하는 첨부파일리스트 가져오기
	 */
	@Override
	public List<MyfileVO> getMyfile(int post_id) {
		return myfileDao.getMyfile(post_id);
	}

	/**
	 * 
	* Method : getFile
	* 작성자 : PC06
	* 변경이력 :
	* @param myfile_id
	* @return
	* Method 설명 : 1개의 첨부파일 정보 가져오기
	 */
	@Override
	public MyfileVO getFile(int myfile_id) {
		return myfileDao.getFile(myfile_id);
	}

	/**
	 * 
	* Method : deleteFile
	* 작성자 : PC06
	* 변경이력 :
	* @param myfile_id
	* @return
	* Method 설명 : 첨부파일 삭제
	 */
	@Override
	public int deleteFile(int myfile_id) {
		MyfileVO myfileVO = myfileDao.getFile(myfile_id);
		boolean result = MyFileUtil.deleteFile(myfileVO);
		logger.debug("File Delete result : {}", result);
		return myfileDao.deleteFile(myfile_id);
	}

}
