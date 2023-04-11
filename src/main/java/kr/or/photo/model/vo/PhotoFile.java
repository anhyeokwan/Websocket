package kr.or.photo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoFile {
	private int photoNo;
	private int photoBoardNo;
	private String filename;
	private String filepath;
}
