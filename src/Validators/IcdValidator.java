package Validators;
import java.util.Random;
import Utils.LogUtils;

public class IcdValidator extends AbstractValidator {

	/**
	 * prints message to different log, with log4j.
	 */
	@Override
	protected void doValidation(String filePath) {
		if (getRandomBoolean()) {
			LogUtils.PrintToLog(filePath, "the file is valid according to random function");
			LogUtils.PrintToLog4j(filePath, "the file is valid according to random function");
		}
		else {
			LogUtils.PrintToLog(filePath, "the file is not valid according to random function");
			LogUtils.PrintToLog4j(filePath, "the file is not valid according to random function");
		}

	}

	private boolean getRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}

}
