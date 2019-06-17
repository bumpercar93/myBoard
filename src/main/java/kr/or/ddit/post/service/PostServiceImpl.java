package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.dao.PostDaoImpl;
import kr.or.ddit.post.model.PostVO;

public class PostServiceImpl implements IPostService {
	
	private IPostDao postDao;
	
	public PostServiceImpl() {
		postDao = new PostDaoImpl();
	}
	
	/**
	 * 
	* Method : getAllPost
	* 작성자 : PC06
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 선택한 게시판의 모든 게시물 가져오기
	 */
	@Override
	public List<PostVO> getAllPost(int board_id) {
		return postDao.getAllPost(board_id);
	}

	/**
	 * 
	* Method : insertPost
	* 작성자 : PC06
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시물 추가하기
	 */
	@Override
	public int insertPost(PostVO postVO) {
		return postDao.insertPost(postVO);
	}

	/**
	 * 
	* Method : updatePost
	* 작성자 : PC06
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시물 수정하기
	 */
	@Override
	public int updatePost(PostVO postVO) {
		return postDao.updatePost(postVO);
	}

	/**
	 * 
	* Method : getPost
	* 작성자 : PC06
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 게시물 한개 정보 가져오기
	 */
	@Override
	public PostVO getPost(int post_id) {
		return postDao.getPost(post_id);
	}

	/**
	 * 
	* Method : postPagingList
	* 작성자 : PC06
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 게시글 페이징리스트
	 */
	@Override
	public Map<String, Object> postPagingList(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int board_id = (int) map.get("board_id");
		int pageSize = (int) map.get("pageSize");
		int postCnt = postDao.postCnt(board_id);
		int paginationSize = (int) Math.ceil((double)postCnt/pageSize);
		
		resultMap.put("postList", postDao.postPagingList(map));
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	/**
	 * 
	* Method : findMax
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : post_id중에 가장 큰 값 찾기(가장 마지막 시퀀스 값)
	 */
	@Override
	public int findMax() {
		return postDao.findMax();
	}

	/**
	 * 
	* Method : deletePost
	* 작성자 : PC06
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 게시물 삭제(디비에서는 삭제하지 않고 구분만 바꿈, 결국 update)
	 */
	@Override
	public int deletePost(int post_id) {
		return postDao.deletePost(post_id);
	}

	/**
	 * 
	* Method : groupSeq
	* 작성자 : PC06
	* 변경이력 :
	* @param pVO
	* @return
	* Method 설명 : 그룹 시퀀스 추가(update구문)
	 */
	@Override
	public int groupSeq(PostVO pVO) {
		return postDao.groupSeq(pVO);
	}

}
