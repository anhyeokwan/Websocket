package kr.or.photo.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.photo.model.dao.PhotoDao;
import kr.or.photo.model.vo.Photo;
import kr.or.photo.model.vo.PhotoFile;

@Service
public class PhotoService {
	@Autowired
	private PhotoDao dao;

	public ArrayList<Photo> selectAllPhoto() {
		ArrayList<Photo> list = dao.selectAllPhoto();
		
		if(list == null) {
			return null;
		}else {
			return list;
		}
	}

	public int insertPhoto(Photo photo, ArrayList<PhotoFile> list) {
		int result = dao.insertPhoto(photo);
		
		if(result > 0) {
			int maxNum = dao.selectMaxNum();
			for(PhotoFile pf : list) {
				pf.setPhotoBoardNo(maxNum);
				result += dao.insertPhotoFile(pf);
			}
		}
		
		return result;
	}
}
