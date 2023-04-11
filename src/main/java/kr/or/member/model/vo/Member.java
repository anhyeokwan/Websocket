package kr.or.member.model.vo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int memberNo;
	   private String memberId;
	   private String memberPw;
	   private String memberName;
	   private String phone;
	   private String email;
}
