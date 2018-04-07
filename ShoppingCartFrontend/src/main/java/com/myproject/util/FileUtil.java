package com.myproject.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component(value="fileUtil")
public class FileUtil {

//	@Autowired
//	HttpSession httpSession;
	
//	public static boolean copyFile(MultipartFile file, String filename)
//	{
//		if (!file.isEmpty()) {
//			try {
//				byte[] bytes = file.getBytes();
//
//				// Creating the directory to store file
//				String rootPath = System.getProperty("catalina.home");
//				File dir = new File(rootPath + File.separator + "filename");
//				if (!dir.exists())
//					dir.mkdirs();
//
//				// Create the file on server
//				File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);
//				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//				stream.write(bytes);
//				stream.close();
//
//				logger.info("Server File Location=" + serverFile.getAbsolutePath());
//
//				return true;
//			} catch (Exception e) {
//				return false;
//			}
//		} else {
//			return false;
//		}
//	}
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	private static Path path;
	public static boolean fileCopyNIO(MultipartFile file, String fileName,HttpServletRequest request) {
		
		String rootDirectory= request.getSession().getServletContext().getRealPath("/");
		path = Paths.get(rootDirectory + "\\resources\\images\\ShoppingCartImages\\"+fileName);
		
		
		File dest = new File(path.toString());
		System.out.println("Where is it uploading?"+ dest.getAbsolutePath());
		if (!dest.exists()) {
			dest.mkdir();
		}

		try {
			file.transferTo(dest);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
