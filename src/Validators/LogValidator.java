package Validators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Utils.LogUtils;
import Validators.XML.Extension;
import Validators.XML.Extensions;

/**
 * Validator for .log files.
 * Read XML configuration file (confFile) and write logs according to it.
 */
public class LogValidator extends AbstractValidator {

	private final static String confFile = "src/Validators/XML/ExtensionsScheme.xml";
	private final static Extensions confObject;

	static {
		confObject = initConfObject();
	}

	/**
	 * Search for the words that comes from the XML file,
	 * if row in file contains all the words - prints relevant message from the XML to the log file.
	 */
	@Override
	protected void doValidation(String filePath) throws IOException {
		List<String> currentFileData = Files.readAllLines(Paths.get(filePath));

		if (isConfObjectValid()) {
			for (int i=0; i<currentFileData.size(); i++) {
				for (Extension ext : confObject.getListOfExtensions()) {
					if (isWordsExsits(ext.getWord(), currentFileData.get(i))) {
						LogUtils.PrintToLog(filePath, ext.getMessage() + " " + (i+1));
					}
				}
			}
		}
	}

	private Boolean isWordsExsits(List<String> words, String row) {
		boolean res = true;
		for (String word : words) {
			if (!row.contains(word)) {
				res = false;
			}
		}
		return res;
	}

	private static Extensions initConfObject() {
		Extensions resConfObject = null;

		try {
			File file = new File(confFile);
			JAXBContext jaxbContext = JAXBContext.newInstance(Extensions.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			resConfObject = (Extensions) jaxbUnmarshaller.unmarshal(file);

		}

		catch (JAXBException confObject) {
			LogUtils.PrintToLog(confFile, "can not parse xml");
			resConfObject = null;
		}

		return resConfObject;
	}
	
	private boolean isConfObjectValid() {
		return confObject != null;
	}

}