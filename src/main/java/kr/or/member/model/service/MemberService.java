package kr.or.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.board.model.vo.Board;
import kr.or.common.LogAdvice1;
import kr.or.common.LogAdvice2;
import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MemberMajor;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	
	public Member selectOneMember(Member member) {
		System.out.println("selectOneMember 메소드 시작");
		Member m = dao.selectOneMember(member);
		System.out.println("selectOneMember 메소드 끝");
		return m;
	}

	public ArrayList<Member> selectAllMember() {
		System.out.println("메소드 시작");
		ArrayList<Member> list = dao.selectAllMember();
		System.out.println("메소드 끝");
		return list;
	}
	
	@Transactional
	public int insertMember(Member m) {
		int result = dao.insertMember(m);
		return result;
	}
	
	@Transactional
	public int deleteMember(int memberNo) {
		return dao.deleteMember(memberNo);
	}

	@Transactional
	public int updateMember(Member m) {
		return dao.updateMember(m);
	}

	public ArrayList<Member> searchMemberName(String memberName) {
		ArrayList<Member> list = dao.searchMemberName(memberName);
		
		if(list == null) {
			return null;
		}else {
			return list;
		}
		
	}

	public ArrayList<Member> searchMember(String type, String keyword) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("keyword", keyword);
		ArrayList<Member> list = dao.searchMember1(map);
		return list;
	}

	public ArrayList<Member> searchMember2(Member m) {
		
		return dao.searchMember2(m);
	}

	public ArrayList<String> selectAllId() {
		return dao.selectAllId();
	}

	public ArrayList<Member> searchMember3(String[] memberId) {
		
		return dao.searchMember3(memberId);
	}

	public ArrayList<Member> searchMember4() {
		return dao.searchMember4();
	}

	public Member selectMemberPw(Member m) {
		
		return dao.selectOnePw(m);
	}

	public int updatePassword(Member m) {
		return dao.updatePassword(m);
	}

	public int insertMemberMajor(MemberMajor mj) {
		int result = dao.insertMemberMajor(mj);
		return result;
	}

}






















