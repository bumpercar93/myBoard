package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.post.model.PostVO;

public class PostDaoImpl implements IPostDao {

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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVO> postList = sqlSession.selectList("post.getAllPost", board_id);
		sqlSession.close();
		return postList;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("post.insertPost", postVO);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("post.updatePost", postVO);
		sqlSession.commit();
		sqlSession.close();
		return updateCnt;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		PostVO postVO = sqlSession.selectOne("post.getPost", post_id);
		sqlSession.close();
		return postVO;
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
	public List<PostVO> postPagingList(Map<String, Object> map) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVO> postPagingList = sqlSession.selectList("post.postPagingList", map);
		sqlSession.close();
		return postPagingList;
	}

	/**
	 * 
	* Method : postCnt
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 전체 수 조회
	 */
	@Override
	public int postCnt(int board_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int postCnt = sqlSession.selectOne("post.postCnt", board_id);
		sqlSession.close();
		return postCnt;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int max = sqlSession.selectOne("post.findMax");
		sqlSession.close();
		return max;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.update("post.deletePost", post_id);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.update("groupSeq", pVO);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

}
