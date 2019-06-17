package kr.or.ddit.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Part;

import kr.or.ddit.myfile.model.MyfileVO;
import kr.or.ddit.myfile.service.IMyfileService;
import kr.or.ddit.myfile.service.MyfileServiceImpl;

public class MyFileUtil {
	
	private static IMyfileService myfileService;
	
	static{
		myfileService = new MyfileServiceImpl();
	}
	
	public static void fileUpload(Part file, int post_id) throws IOException {
		String contentDisposition = file.getHeader("content-disposition");
		String filename = PartUtil.getFileName(contentDisposition);
		String ext = PartUtil.getExt(filename);
		String uploadPath = PartUtil.getUploadPath();
		
		// 파일 디스크에 쓰기
		String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
		MyfileVO myfileVO = new MyfileVO(post_id, filePath, filename);
		myfileService.insertMyfile(myfileVO);
		
		file.write(filePath);
		file.delete(); // 임시파일 지우기
	}
	
	public static boolean deleteFile(MyfileVO myfileVO) {
		boolean result = false;
		File file = new File(myfileVO.getMyfile_path());
		
		if(file.exists())
			result = file.delete();
		
		return result;
		
	}
	
}
