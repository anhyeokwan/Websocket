package kr.or.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;
import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MemberMajor;

@Repository
public class MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public Member selectOneMember(Member member) {
		Member m = sqlSession.selectOne("member.selectOneMember", member); // 결과값이 한줄일때
		
		return m;
	}

	public ArrayList<Member> selectAllMember() {
		List list = sqlSession.selectList("member.selectAllMember");
		return (ArrayList<Member>)list;
	}

	public int insertMember(Member m) {
		int result = sqlSession.insert("member.insertMember", m);
		return result;
	}

	public int deleteMember(int memberNo) {
		int result = sqlSession.delete("member.deleteMember", memberNo);
		return result;
	}

	public int updateMember(Member m) {
		int result = sqlSession.update("member.updateMember", m);
		return result;
	}

	public ArrayList<Member> searchMemberName(String memberName) {
		List list  = sqlSession.selectList("member.searchMemberName", memberName);
		if(list.isEmpty()) {
			return null;
		}else {
			return (ArrayList<Member>)list;
		}
		
	}

	public ArrayList<Member> searchMember1(HashMap<String, Object> map) {
		List list = sqlSession.selectList("member.searchMember", map);
		return (ArrayList<Member>)list;
	}

	public ArrayList<Member> searchMember2(Member m) {
		List list = sqlSession.selectList("member.searchMember2", m);
		return (ArrayList<Member>)list;
	}

	public ArrayList<String> selectAllId() {
		List list = sqlSession.selectList("member.selectAllId");
		return (ArrayList<String>)list;
	}

	public ArrayList<Member> searchMember3(String[] memberId) {
		List list = sqlSession.selectList("member.searchMember3", memberId);
		//HashMap<String, Object> map = new HashMap<String, Object>();
		//map.put("array",memberId);
		//List list = sqlSession.selectList("member.searchMember3", map);
		
		return (ArrayList<Member>)list;
	}

	public ArrayList<Member> searchMember4() {
		List list = sqlSession.selectList("member.searchMember4");
		return (ArrayList<Member>)list;
	}

	public Member selectOnePw(Member m) {
		Member member = sqlSession.selectOne("member.searchMemberPw", m);
		return member;
	}

	public int updatePassword(Member m) {
		int result = sqlSession.update("member.updatePassword", m);
		return result;
	}

	public int insertMemberMajor(MemberMajor mj) {
		int result = sqlSession.insert("member.insertMemberMajor", mj);
		return result;
	}

}





















