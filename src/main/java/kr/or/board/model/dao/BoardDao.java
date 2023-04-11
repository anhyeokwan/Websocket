package kr.or.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.FileVo;
import kr.or.photo.model.vo.Photo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public ArrayList<Board> selectBoardList(HashMap<String, Object> map) {
		List list = sqlsession.selectList("board.selectBoardList", map);
		return (ArrayList<Board>)list;
	}

	public int selectBoardCount() {
		int totalCount = sqlsession.selectOne("board.totalCount");
		return totalCount;
	}

	public Board selectOneBoard(int boardNo) {
		
		return sqlsession.selectOne("board.selectOneBoard", boardNo);
	}

	public ArrayList<FileVo> selectFileList(int boardNo) {
		List list = sqlsession.selectList("board.selectFileList", boardNo);
		return (ArrayList<FileVo>)list;
	}

	public int insertBoard(Board b) {
		int result = sqlsession.insert("board.insertBoard", b);
		return result;
	}

	public int selectBoardNo() {
		int result = sqlsession.selectOne("board.selectBoardNo");
		return result;
	}

	public int insertFile(FileVo fileVo) {
		int result = sqlsession.insert("file.insertFile", fileVo);
		return result;
	}

}


















