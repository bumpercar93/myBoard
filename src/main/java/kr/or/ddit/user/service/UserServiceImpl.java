package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVO;

public class UserServiceImpl implements IUserService {
	
	private IUserDao userDao;
	
	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}
	
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
		List<UserVO> userList = userDao.userList();
		return userList;
	}
	
	/**
	* 
	* Method : getUser
	* 작성자 : PC06
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	@Override
	public UserVO getUser(String userId) {
		UserVO userVO = userDao.getUser(userId);
		return userVO;
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
	public Map<String, Object> userPagingList(PageVO pageVO) {
		// 1. List<UserVO>, userCnt를 필드로 하는 VO 생성
		// 2. List<Object>를 이용
		// 3. Map<String Object>를 이용
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("userList", userDao.userPagingList(pageVO));
		
		// UsersCnt --> paginationSize 변경
		int usersCnt = userDao.usersCnt();
		// pageSize --> pageVO.getPageSize();
		int paginationSize = (int) Math.ceil((double)usersCnt/pageVO.getPageSize());
		
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
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
		return userDao.insertUser(userVO);
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
		return userDao.deleteUser(userId);
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
		return userDao.updateUser(userVO);
	}
	

}
