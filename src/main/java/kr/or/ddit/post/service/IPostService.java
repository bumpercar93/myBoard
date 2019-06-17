package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.PostVO;

public interface IPostService {

	/**
	 * 
	* Method : getAllPost
	* 작성자 : PC06
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 선택한 게시판의 모든 게시물 가져오기
	 */
	List<PostVO> getAllPost(int board_id);

	/**
	 * 
	* Method : insertPost
	* 작성자 : PC06
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시물 추가하기
	 */
	int insertPost(PostVO postVO);

	/**
	 * 
	* Method : updatePost
	* 작성자 : PC06
	* 변경이력 :
	* @param postVO
	* @return
	* Method 설명 : 게시물 수정하기
	 */
	int updatePost(PostVO postVO);

	/**
	 * 
	* Method : getPost
	* 작성자 : PC06
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 게시물 한개 정보 가져오기
	 */
	PostVO getPost(int post_id);

	/**
	 * 
	* Method : postPagingList
	* 작성자 : PC06
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 게시글 페이징리스트
	 */
	Map<String, Object> postPagingList(Map<String, Object> map);
	
	/**
	 * 
	* Method : findMax
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : post_id중에 가장 큰 값 찾기(가장 마지막 시퀀스 값)
	 */
	int findMax();
	
	/**
	 * 
	* Method : deletePost
	* 작성자 : PC06
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 게시물 삭제(디비에서는 삭제하지 않고 구분만 바꿈, 결국 update)
	 */
	int deletePost(int post_id);
	
	/**
	 * 
	* Method : groupSeq
	* 작성자 : PC06
	* 변경이력 :
	* @param pVO
	* @return
	* Method 설명 : 그룹 시퀀스 추가(update구문)
	 */
	int groupSeq(PostVO pVO);
}
