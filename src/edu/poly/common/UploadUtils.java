package edu.poly.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

public class UploadUtils {
	public static String processUploadField(String fieldName, HttpServletRequest request, String storedFolder,
			String storedFilename) throws IOException, ServletException {
		Part filePart = request.getPart(fieldName);

		if (filePart == null || filePart.getSize() == 0) {
			return "";
		}

		if (storedFolder == null) {
			storedFolder = "/uploads"; // (/images)
		}

		if (storedFilename == null) {
			storedFilename = filePart.getSubmittedFileName();
		} else {
			storedFilename += "." + FilenameUtils.getExtension(Paths.get(filePart.getSubmittedFileName()).toString());
		}

		String uploadFolder = request.getServletContext().getRealPath(storedFolder);

		Path uploadPath = Paths.get(uploadFolder);

		if (!Files.exists(uploadPath)) {
			Files.createDirectory(uploadPath);
		}
		filePart.write(Paths.get(uploadPath.toString(), storedFilename).toString());

		return storedFilename;
	}
}
