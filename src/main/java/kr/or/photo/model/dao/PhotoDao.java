package kr.or.photo.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.photo.model.vo.Photo;
import kr.or.photo.model.vo.PhotoFile;

@Repository
public class PhotoDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public ArrayList<Photo> selectAllPhoto() {
		List list = sqlSession.selectList("board.selectAllPhoto");
		
		if(list.isEmpty()) {
			return null;
		}else {
			return (ArrayList<Photo>)list;
		}
	}

	public int insertPhoto(Photo photo) {
		int result = sqlSession.insert("board.insertPhoto", photo);
		return result;
	}

	public int selectMaxNum() {
		int result  = sqlSession.selectOne("board.selectMaxNum");
		return result;
	}

	public int insertPhotoFile(PhotoFile pf) {
		int result = sqlSession.insert("board.insertPhotoFile", pf);
		return result;
	}
}
