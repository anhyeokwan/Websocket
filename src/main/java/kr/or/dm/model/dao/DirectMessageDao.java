package kr.or.dm.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.dm.model.vo.DirectMessage;

@Repository
public class DirectMessageDao {
	@Autowired
	private SqlSession sqlsession;

	public int insertDm(DirectMessage dm) {
		int result = sqlsession.insert("directMessage.insertDm", dm);
		return result;
	}

	public ArrayList<DirectMessage> selectAllSend(DirectMessage dm) {
		List list = sqlsession.selectList("directMessage.selectAllSend", dm);
		return (ArrayList<DirectMessage>)list;
	}

	public DirectMessage selectOneDm(int dmNo) {
		
		return sqlsession.selectOne("directMessage.selectOneDm", dmNo);
	}

	public void updateReadCheck(int dmNo) {
		sqlsession.update("directMessage.updateReadCheck", dmNo);
		
	}

	public int dmCount(String memberId) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("directMessage.dmCount", memberId);
	}
}





















