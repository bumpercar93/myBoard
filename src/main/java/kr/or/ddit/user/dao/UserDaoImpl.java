package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;

public class UserDaoImpl implements IUserDao{

	/**
	* 
	* Method : userList
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 조회
	*/
	@Override
	public List<UserVO> userList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<UserVO> userList = sqlSession.selectList("user.userList");
		sqlSession.close();
		return userList;
	}
	
	/**
	* 
	* Method : getUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 특정 사용자 정보 조회
	*/
	@Override
	public UserVO getUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		UserVO uVO = sqlSession.selectOne("user.getUser", userId);
		sqlSession.close();
		return uVO;
	}

	/**
	 * 
	* Method : userPagingList
	* 작성자 : PC06
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Override
	public List<UserVO> userPagingList(PageVO pageVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<UserVO> userList = sqlSession.selectList("user.userPagingList", pageVO);
		sqlSession.close();
		return userList;
	}
	
	/**
	 * 
	* Method : usersCnt
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체수 조회
	 */
	@Override
	public int usersCnt() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int usersCnt = sqlSession.selectOne("user.usersCnt");
		sqlSession.close();
		return usersCnt;
	}

	/**
	 * 
	* Method : insertUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 등록
	 */
	@Override
	public int insertUser(UserVO userVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("user.insertUser", userVO);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}
	
	/**
	 * 
	* Method : deleteUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	 */
	@Override
	public int deleteUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteCnt = sqlSession.delete("user.deleteUser", userId);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}
	
	/**
	 * 
	* Method : updateUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userVO
	* @return
	* Method 설명 : 사용자 수정
	 */
	@Override
	public int updateUser(UserVO userVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("user.updateUser", userVO);
		sqlSession.commit();
		sqlSession.close();
		return updateCnt;
	}

	/**
	 * 
	* Method : userListForPassEncrypt
	* 작성자 : PC06
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 비밀번호 암호화 적용대상 사용자 전체 조회
	 */
	@Override
	public List<UserVO> userListForPassEncrypt(SqlSession sqlSession) {
		return sqlSession.selectList("user.userListForPassEncrypt");
	}

	/**
	 * 
	* Method : updateUserEncryptPass
	* 작성자 : PC06
	* 변경이력 :
	* @param sqlSession
	* @param userVO
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용
	 */
	@Override
	public int updateUserEncryptPass(SqlSession sqlSession, UserVO userVO) {
		return sqlSession.update("user.updateUserEncryptPass", userVO);
	}

}
