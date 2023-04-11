package common;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class Filename {
	// 중복체크하는 코드
	public String fileReName(String savePath, String filename) {
		String onlyFilename = filename.substring(0, filename.indexOf("."));
		String extention = filename.substring(filename.indexOf("."));
		
		String filepath = null;
		int count = 0;
		while(true) {
			if(count != 0) {
				filepath = onlyFilename + "_" + count + extention;
			}else {
				filepath = onlyFilename + extention;
			}
			
			File fileCheck = new File(savePath, filepath);
			if(!fileCheck.exists()) {
				break;
			}
			count++;
		}
		return filepath;
	}

}
