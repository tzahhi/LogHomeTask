package Validators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import Utils.LogUtils;

public class FolderValidator extends AbstractValidator {
	private int MAX_NUMBER_OF_FILE = 100;
	private static final String appPropFile = "app.prop";
	private static final String failedFile = "failed";
	private int failedFilesCounter = 0;
	private int numberOfFilesInFolder = 0;

	@Override
	protected void doValidation(String filePath) throws IOException {
		numberOfFilesInFolder = new File(filePath).listFiles().length;
		checkIfPropFileExistsAndCountFailedFiles(filePath);
	}

	/**
	 * Search for app.prop file in the current directory, and count .failed files.
	 */
	public void checkIfPropFileExistsAndCountFailedFiles(String folderPath) throws IOException {
		File folder = new File(folderPath);
		File[] folderContents = folder.listFiles();

		for (File f : folderContents) {
			if (f.getName().equals(appPropFile)) {
				updateMaxNumberOfFiles(f);
			} else if (AbstractValidator.getFileExt(f.getName()).equals(failedFile)) {
				failedFilesCounter++;
			}
		}
		
		if(numberOfFilesInFolder > MAX_NUMBER_OF_FILE) {
			LogUtils.PrintToLog(folderPath, new String("Too much files in the folder, over the MAX_NUMBER_OF_FILE"));
		}
		
		LogUtils.PrintToLog(folderPath, new String("include " + numberOfFilesInFolder + " files"));
		LogUtils.PrintToLog(folderPath, new String(failedFilesCounter + " files with .failed extension"));
	}

	/**
	 * 
	 * @param propFile - the properties file path
	 * @throws IOException
	 */
	private void updateMaxNumberOfFiles(File propFile) throws IOException {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(propFile.getPath());

			prop.load(input);

			String maxNumberOfFile = prop.getProperty("MaxNumberOfFile");
			if(maxNumberOfFile != null) {
				if(Integer.parseInt(maxNumberOfFile) > 0) {
					MAX_NUMBER_OF_FILE = Integer.parseInt(maxNumberOfFile);
				}
			}
		}

		catch (IOException | NumberFormatException ex) {
			LogUtils.PrintToLog(appPropFile, "error read data from maxNumberOfFile property");
		}
		
		finally {
			input.close();
		}

	}
}
