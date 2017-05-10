package Validators;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.bind.JAXBException;

import Utils.LogUtils;

public class ValidatorProg {

	public static void main(String[] args) throws JAXBException, IOException {

		for (String path : args) {
			try {
				AbstractValidator validtor = AbstractValidator.Create(path);
				if(validtor != null)
					validtor.doValidation(path);
			}
			
			catch (FileNotFoundException e) {
				LogUtils.PrintToLog(path, new String("The file not found"));
			}

			catch (Exception e) {
				LogUtils.PrintToLog(path, new String("Unsupported extension"));
			}
		}
	}
}
