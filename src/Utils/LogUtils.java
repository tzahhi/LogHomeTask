package Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;;

/**
 * Class for writing logs to file.
 */
public class LogUtils {
	
	// Path for the regular logs
	private static final String FILENAME = "logFile.log";
	
	//Logger for IcdValidator's logs
	final static Logger logger4j = Logger.getLogger("Validators");
	
	/**
	 * The function gets folder name and error and write the error to the logs file.
	 * 
	 * try-with-resources Statement, no need to close()
	 * BufferedWriter implement java.lang.AutoCloseable
	 */
	public static void PrintToLog(String folder, String error) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {	
			bw.write(folder + " : " + error + "\n");
			System.out.println(folder + " : " + error);
		}
		catch (IOException e) {
			System.out.println("error with log file");
		}
	}
	
	public static void PrintToLog4j(String folder, String error) {
		logger4j.info(folder + " : " + error);
	}
}
