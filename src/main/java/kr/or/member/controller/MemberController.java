package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MemberMajor;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/login.do")
	public String login(Member member, HttpSession session) {
		Member m = service.selectOneMember(member);
		if(m != null) {
			session.setAttribute("m", m);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/selectAllMember.do")
	public String selectAllMember(Model model) {
		ArrayList<Member> list = service.selectAllMember();
		
		model.addAttribute("list", list);
		return "member/memberList";
	}
	
	@RequestMapping(value="/joinFrm.do")
	public String joinFrm() {
		return "member/joinFrm";
	}
	
	@RequestMapping(value="/logOut.do")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/join.do")
	public String join(Member m) {
		int result = service.insertMember(m);
		
		if(result > 0) {
			return "redirect:/";
		}else {
			return "member/joinFrm";
		}
	}
	
	@RequestMapping(value="/searchMemberId.do")
	public String searchMemberid(Member member, Model model) {
		Member m = service.selectOneMember(member);
		
		if(m != null) {
			model.addAttribute("m", m);
			return "member/searchMember";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/mypage.do")
	public String mypage() {
		return "member/mypage";
	}
	
	@RequestMapping(value="/delete.do")
	public String deleteMember(int memberNo) {
		int result = service.deleteMember(memberNo);
		
		if(result > 0) {
			return "redirect:/logOut.do";
		}else {
			return "redirect:/mypage.do";
		}
	}
	
	@RequestMapping(value="/updateFrm.do")
	public String updateFrm() {
		return "member/updateFrm";
	}
	
	@RequestMapping(value="/updateMember.do")
	public String updateMember(Member m, HttpSession session, Model model) {
		int result = service.updateMember(m);
		
		if(result > 0) {
			Member member = (Member)session.getAttribute("m");
			member.setMemberPw(m.getMemberPw());
			member.setPhone(m.getPhone());
			member.setEmail(m.getEmail());
			model.addAttribute("m", m);
			return "redirect:/mypage.do";
		}else {
			return "redirect:/mypage.do";
		}
	}
	
	@RequestMapping(value="/searchMemberName.do")
	public String searchMemberName(String memberName, Model model) {
		ArrayList<Member> list = service.searchMemberName(memberName);
		
		if(list == null) {
			model.addAttribute("msg", "일치하는 이름이 없습니다.");
			return "member/searchMemberList";
		}else {
			model.addAttribute("list", list);
			return "member/searchMemberList";
		}
	}
	
	@RequestMapping(value="/searchMember1.do")
	public String searchMember(String type, String keyword, Model model) {
		ArrayList<Member> list = service.searchMember(type, keyword);
		model.addAttribute("list", list);
		return "member/searchMemberList";
	}
	
	@RequestMapping(value="/searchMember2.do")
	public String searchMember2(Member m, Model model) {
		ArrayList<Member> list = service.searchMember2(m);
		model.addAttribute("list", list);
		return "member/searchMemberList";
	}
	
	@RequestMapping(value="/idList.do")
	public String idList(Model model) {
		ArrayList<String> list = service.selectAllId();
		model.addAttribute("list", list);
		return "member/idList";
	}
	
	@RequestMapping(value="/searchMember3.do")
	public String searchMember3(String[] memberId, Model model) {
		ArrayList<Member> list = service.searchMember3(memberId);
		model.addAttribute("list", list);
		return "member/searchMemberList";
	}
	
	@RequestMapping(value="/searchMember4.do")
	public String searchMember4(Model model) {
		ArrayList<Member> list = service.searchMember4();
		
		model.addAttribute("list",list);
		return "member/searchMemberList";
	}
	
	@ResponseBody // 비동기 요청(데이터 자체를 요청할때)
	@RequestMapping(value="/idcheck.do")
	public String idcheck(Member m) {
		Member member = service.selectOneMember(m);
		if(member == null) {
			// 사용가능한 아이디
			return "0";
		}else {
			// 이미 사용중인 아이디
			return "1";
		}
	}
	
	@ResponseBody // 페이지 말고 내가 조회한 데이터자체를 보내준다.
	@RequestMapping(value="/ajaxAllMember.do", produces = "application/json;charset=utf-8")
	public String ajaxAllMember() {
		ArrayList<Member> list = service.selectAllMember();
		Gson gson = new Gson();
		String result = gson.toJson(list);// retrun이 String
		return result;
	}
	
	@RequestMapping(value="/updatePasswordFrm.do")
	public String updatePasswordFrm() {
		
		return "member/updatePasswordFrm";
	}
	
	@ResponseBody
	@RequestMapping(value="/checkPw.do")
	public String pwCheck(Member m) {
		Member pw = service.selectMemberPw(m);
		if(pw == null) {
			return "0";
		}else {
			return "1";
		}
	}
	
	@RequestMapping(value="/updatePassword.do")
	public String updatePassword(Member m, HttpSession session, Model model) {
		int result = service.updatePassword(m);
		
		if(result > 0) {
			Member member = (Member)session.getAttribute("m");
			member.setMemberPw(m.getMemberPw());
			return "redirect:/logOut.do";
		}else {
			return "redirect:/mypage.do";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/checkIdEmail.do")
	public String checkIdEmail(Member m) {
		Member member = service.selectOneMember(m);
		
		if(member != null) {
			return "1";
		}else {
			return "0";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/sendMail.do")
	public String sendMail(String email, Model model) {
		
		MailSender sender = new MailSender();
		String randomCode = sender.sendMail(email);
		
		return randomCode;
	}
	
	@RequestMapping(value="/updatePwFrm.do")
	public String updatePwFrm() {
		return "member/updatePwFrm";
	}
	
	@RequestMapping(value="/allMemberChat.do")
	public String allMemberChat() {
		return "member/allChat";
	}
	
	@ResponseBody
	@RequestMapping(value="selectAllmemberId.do", produces = "application/json;charset=utf-8")
	public String selectAllmemberId() {
		ArrayList<String> list = service.selectAllId();
		return new Gson().toJson(list);
	}
	
	@RequestMapping(value="/insertMajor.do")
	public String insertMajor(MemberMajor mj) {
		int result = service.insertMemberMajor(mj);
		
		return "redirect:/";
	}
}


























