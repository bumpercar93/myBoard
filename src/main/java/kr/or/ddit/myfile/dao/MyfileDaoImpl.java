package kr.or.ddit.myfile.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.myfile.model.MyfileVO;

public class MyfileDaoImpl implements IMyfileDao {
	
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("myfile.insertMyfile", myfileVO);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<MyfileVO> myfileList = sqlSession.selectList("myfile.getMyfile", post_id);
		sqlSession.close();
		return myfileList;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		MyfileVO myfileVO = sqlSession.selectOne("myfile.getFile", myfile_id);
		sqlSession.close();
		return myfileVO;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteCnt = sqlSession.delete("myfile.deleteFile", myfile_id);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}

}
