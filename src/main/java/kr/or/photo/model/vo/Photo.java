package kr.or.photo.model.vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
	private int photoBoardNo;
	private String photoBoardWriter;
	private String photoBoardContent;
	private ArrayList<Photo> photoList;
}
