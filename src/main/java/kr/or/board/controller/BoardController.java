package kr.or.board.controller;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.Filename;
import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.FileVo;
import kr.or.member.model.service.MemberService;
import kr.or.photo.model.vo.Photo;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	@Autowired
	private Filename fileRename;
	
	@RequestMapping(value="/boardList.do")
	public String boardList(int reqPage, Model model) {
		HashMap<String, Object> map = service.selectBoardList(reqPage);
		model.addAttribute("list", (ArrayList<Board>)map.get("list"));
		model.addAttribute("pageNavi", map.get("pageNavi"));
		model.addAttribute("reqPage", map.get("reqPage"));
		model.addAttribute("numPerPage", map.get("numPerPage"));
		return "board/boardList";
	}
	
	@RequestMapping(value="/boardView.do")
	public String boardView(int boardNo, Model model) {
		Board b = service.selectOneBoard(boardNo);
		model.addAttribute("b", b);
		return "board/boardView";
	}
	
	@RequestMapping(value="/baordWriteFrm.do")
	public String boardWriteFrm() {
		return "board/baordWriteFrm";
	}
	
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite(Board b, MultipartFile[] boardfile, HttpServletRequest request) {
		// 파일을 담을 리스트 생성
		ArrayList<FileVo> list = new ArrayList<FileVo>();
		
		if(!boardfile[0].isEmpty()) {
			// 파일 경로 설정
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
			
			// 반복문을 이용한 파일업로드
			for(MultipartFile file : boardfile) {
				String filename = file.getOriginalFilename();
				String filepath = fileRename.fileReName(savePath, filename);
				
				try {
					FileOutputStream fos = new FileOutputStream(savePath + filepath);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					byte[] bytes = file.getBytes();
					
					bos.write(bytes);
					bos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				FileVo fv = new FileVo();
				fv.setFilename(filename);
				fv.setFilepath(filepath);
				list.add(fv);
			}
		}
		
		int result = service.insertBoard(b,list);
		
		return "redirect:/boardList.do?reqPage=1";
	}
	
	
}
























