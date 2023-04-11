package kr.or.dm.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.dm.model.dao.DirectMessageDao;
import kr.or.dm.model.vo.DirectMessage;
import kr.or.member.model.dao.MemberDao;

@Service
public class DirectMessageService {
	@Autowired
	private DirectMessageDao dao;
	
	@Transactional
	public int insertDm(DirectMessage dm) {
		int result = dao.insertDm(dm);
		if(result>0) {
	         // insert 성공 시 받은사람의 읽지 않은 쪽지수를 조회해서 리턴
	         result = dao.dmCount(dm.getReceiver());
	         return result;
	      }else {
	         // 실패
	         return -1;
	      }
	}

	public ArrayList<DirectMessage> selectAllSend(DirectMessage dm) {
		
		return dao.selectAllSend(dm);
	}
	
	@Transactional
	public DirectMessage selectOneDm(int dmNo) {
		DirectMessage dm = dao.selectOneDm(dmNo);
		
		if(dm.getReadCheck() == 0) {
			dao.updateReadCheck(dmNo);
		}
		return dm;
	}

	public int dmCount(String memberId) {
		
		return dao.dmCount(memberId);
	}
}
