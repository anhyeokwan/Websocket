package kr.or.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.FileVo;
import kr.or.photo.model.vo.Photo;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;
	
	public HashMap<String, Object> selectBoardList(int reqPage) {
		// 한페이지당 보여줄 게시물
		int numPerPage = 5;
		// reqPage 1 -> 1~5
		// reqPage 2 -> 6 ~10
		int end = numPerPage * reqPage; // 끝번호
		int start = end - numPerPage + 1; // 시작번호
		
		// 계산한 start, end 이용해서 게시물 목록 조회
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		ArrayList<Board> list = dao.selectBoardList(map);
		// pageNavi 시작
		// 전체 게시판수 계산필요 - > 전체 게시물 수
		int totalContent = dao.selectBoardCount();
		
		// 전체페이지 수 계산
		int totalPage = 0;
		if(totalContent % numPerPage == 0) {
			totalPage = totalContent / numPerPage;
		}else {
			totalPage = totalContent / numPerPage + 1;
		}
		
		// 페이지 네비 길이
		int pageNaviSize = 5;
		
		int pageNo = 1;
		
		if(reqPage > 3) {
			pageNo = reqPage - 2;
		}
		
		// pageNavi 생성시작
		String  pageNavi = "";
		if(pageNo != 1) {
			pageNavi += "<a href='/boardList.do?reqPage=" + (pageNo-1) +"'>[이전]</a>";
		}
		
		// 페이지 숫자 생성
		for(int i = 0; i < pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<span>" + pageNo + "</span>";
			}else {
				pageNavi += "<a href='/boardList.do?reqPage=" + pageNo +"'>"+ pageNo +"</a>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		
		// 다음버튼
		if(pageNo <= totalPage) {
			pageNavi += "<a href='/boardList.do?reqPage=" + pageNo + "'>[다음]</a>";
		}
		
		HashMap<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("list", list);
		pageMap.put("pageNavi", pageNavi);
		pageMap.put("reqPage", reqPage);
		pageMap.put("numPerPage", numPerPage);
		return pageMap;
	}

	public Board selectOneBoard(int boardNo) {
		// board 테이블 조회
		Board b = dao.selectOneBoard(boardNo);
		// file_tbl 조회
		// ArrayList<FileVo> fileList = dao.selectFileList(boardNo);
		// b.setFileList(fileList);
		return b;
	}

	public int insertBoard(Board b, ArrayList<FileVo> list) {
		int result = dao.insertBoard(b);
		if(result > 0) {
			int boardNo = 0;
			
			if(!list.isEmpty()) {
				boardNo = dao.selectBoardNo();
				for(FileVo fileVo : list) {
					fileVo.setBoardNo(boardNo);
					result += dao.insertFile(fileVo);
				}
			}
		}
		
		return result;
	}

}






















