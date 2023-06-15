package kr.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUtil {
	//인코딩 타입
	private static final String ENCODING_TYPE = "utf-8";
	//최대 업로드 사이즈
	private static final int MAX_SIZE = 20*1024*1024;
	//업로드 상대 경로
	private static final String UPLOAD_PATH = "/upload";
	
	//파일 업로드
	public static MultipartRequest createFile(
			         HttpServletRequest request)
	                        throws IOException{
		//업로드 절대 경로
		String upload =
			request.getServletContext().getRealPath(UPLOAD_PATH);
		
		return new MultipartRequest(request,
				upload,MAX_SIZE,ENCODING_TYPE,
				new DefaultFileRenamePolicy());
	}
	//파일 삭제
	public static void removeFile(
			HttpServletRequest request,
			      String filename) {
		if(filename!=null) {
			//업로드 절대 경로
			String upload = 
					request.getServletContext().getRealPath(UPLOAD_PATH);
			File file = 
					new File(upload + "/" + filename);
			if(file.exists()) file.delete();
		}
	}
	
	
}



