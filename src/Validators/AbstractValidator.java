package Validators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/** 
 * 
 * Abstract class of Validator.
 * Has an abstract function doValidation for polymorphic base.
 */
public abstract class AbstractValidator {

	/**
	 * 
	 * Static factory method to create the instance of the relevant validator,
	 * according to the file extension.
	 */
	public static AbstractValidator Create(String filePath) throws UnsupportedException, FileNotFoundException {

		String fileExt = "";
		File file = new File(filePath);
		eSupportedExtensions enumConversion = null;
		
		// If file not exists, raise exception 
		if(!checkFileExistence(filePath)) {
			throw new FileNotFoundException();
		}

		// Determine if it's a file or directory - and act as needed.
		if (file.isDirectory()) { // Directory
			enumConversion = eSupportedExtensions.FOLDER;
		}
		else { // File
			fileExt = getFileExt(filePath);
			fileExt = fileExt.toUpperCase();
			// If the file extension is not in the eSupportedExtension, raise exception
			enumConversion = eSupportedExtensions.valueOf(fileExt);
			
		}

		// Create actual class
		switch (enumConversion) {
		case ICD:
			return new IcdValidator();
		case LOG:
			return new LogValidator();		
		case FOLDER:
			return new FolderValidator();
		default:
			// Just in case
			 throw new UnsupportedException("file extension " + fileExt + "not supported.");
		}
	}
	
	/**
	 * Abstract function to be overridden in extended classes
	 */
	protected abstract void doValidation(String filePath) throws IOException;

	private static boolean checkFileExistence(String filePath) {
		return (new File(filePath).exists());
	}

	protected static String getFileExt(String fileName) {
		int i = fileName.lastIndexOf('.');
		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (i > p) {
			return fileName.substring(i + 1);
		}
		return "";
	}
}
