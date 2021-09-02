package com.board.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import net.coobird.thumbnailator.Thumbnails;

public class UploadFileUtils {

	static final int THUMB_WIDTH = 300;
	static final int THUMB_HEIGHT = 300;   // thumb image size

	public static String fileUpload(String uploadPath, String fileName, byte[] fileData, String ymdPath)
			throws Exception {

		UUID uid = UUID.randomUUID();   // get random uuid

		String newFileName = uid + "_" + fileName;   // make file name
		String imgPath = uploadPath + ymdPath;   // make store path

		File target = new File(imgPath, newFileName);   // set new file object with store path and file name
		FileCopyUtils.copy(fileData, target);   // add new file

		String thumbFileName = "s_" + newFileName;   // make thumb file name
		File image = new File(imgPath + File.separator + newFileName);   // set new file object with file name

		File thumbnail = new File(imgPath + File.separator + "s" + File.separator + thumbFileName);   // set new file object with thumb image file name

		if (image.exists()) {   // if image is exist
			thumbnail.getParentFile().mkdirs();   // make thumb image directory
			Thumbnails.of(image).size(THUMB_WIDTH, THUMB_HEIGHT).toFile(thumbnail);   // make thumb image file and store
		}
		return newFileName;
	}

	public static String calcPath(String uploadPath) {
		// set date path name for image file
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);   // make directory of date
		makeDir(uploadPath, yearPath, monthPath, datePath + "\\s");   // make directory of date of thumb image

		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {

		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}   // if directory exist, not make new directory

		for (String path : paths) {
			File dirPath = new File(uploadPath + path);   // get new directory path

			if (!dirPath.exists()) {
				dirPath.mkdir();   // if directory is not exist, make new directory
			}
		}
	}
}