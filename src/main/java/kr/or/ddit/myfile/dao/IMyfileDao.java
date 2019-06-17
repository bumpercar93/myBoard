package kr.or.ddit.myfile.dao;

import java.util.List;

import kr.or.ddit.myfile.model.MyfileVO;

public interface IMyfileDao {
	
	/**
	 * 
	* Method : insertMyfile
	* 작성자 : PC06
	* 변경이력 :
	* @param myfileVO
	* @return
	* Method 설명 : 첨부파일 등록
	 */
	int insertMyfile(MyfileVO myfileVO);
	
	/**
	 * 
	* Method : getMyfile
	* 작성자 : PC06
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 1개의 게시물에 해당하는 첨부파일리스트 가져오기
	 */
	List<MyfileVO> getMyfile(int post_id);
	
	/**
	 * 
	* Method : getFile
	* 작성자 : PC06
	* 변경이력 :
	* @param myfile_id
	* @return
	* Method 설명 : 1개의 첨부파일 정보 가져오기
	 */
	MyfileVO getFile(int myfile_id);
	
	/**
	 * 
	* Method : deleteFile
	* 작성자 : PC06
	* 변경이력 :
	* @param myfile_id
	* @return
	* Method 설명 : 첨부파일 삭제
	 */
	int deleteFile(int myfile_id);
}
