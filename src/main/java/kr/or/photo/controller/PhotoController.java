package kr.or.photo.controller;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.Filename;
import kr.or.photo.model.service.PhotoService;
import kr.or.photo.model.vo.Photo;
import kr.or.photo.model.vo.PhotoFile;

@Controller
public class PhotoController {
	@Autowired
	private PhotoService service;
	
	@Autowired
	private Filename fileName;
	
	@RequestMapping(value="/photoList.do")
	public String photoList(Model model) {
		ArrayList<Photo> list = service.selectAllPhoto();
		
		if(list == null) {
			model.addAttribute("msg", "좀 써라 임마");
		}else {
			model.addAttribute("list", list);
		}
		return "photo/photoList";
	}
	
	@RequestMapping(value="/photoWriteFrm.do")
	public String photoWriteFrm() {
		return "photo/photoWriteFrm";
	}
	
	@RequestMapping(value="/photoWrite.do")
	public String insertPhoto(Photo photo, MultipartFile[] imgFile, HttpServletRequest request) {
		
		ArrayList<PhotoFile> list = new ArrayList<PhotoFile>();
		
		if(!imgFile[0].isEmpty()) {
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/photo/");
			
			for(MultipartFile upFile : imgFile) {
				String filename = upFile.getOriginalFilename();
				String filepath = fileName.fileReName(savePath, filename);
				
				try {
					FileOutputStream fos = new FileOutputStream(savePath + filepath);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					
					byte[] bytes = upFile.getBytes();
					
					bos.write(bytes);
					bos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				PhotoFile pf = new PhotoFile();
				pf.setFilename(filename);
				pf.setFilepath(filepath);
				list.add(pf);
			}
			
		}
		
		
		int result = service.insertPhoto(photo, list);
		
		return "redirect:/photoList.do";
		
	}
}
























