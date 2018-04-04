package com.myproject.util;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

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
	
	private static final String imageDirectory = "ShoppingCartImages";

	private static String rootPath = System.getProperty("catalina.home");

	public static boolean fileCopyNIO(MultipartFile file, String fileName) {
		File dest = new File(rootPath + File.separator + imageDirectory + File.separator + fileName);
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
