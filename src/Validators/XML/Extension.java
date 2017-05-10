package Validators.XML;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Extension {
	
	
	private List<String> Word;
	private String Message;
	
	public Extension(List<String> Word, String Message) {		
		this.Word = Word;
		this.Message = Message;
	}
	
	public Extension() {		
	
	}
	
	public void setMessage(String message) {
		Message = message;
	}
	
	public void setWord(List<String> word) {
		Word = word;
	}
	
	public String getMessage() {
		return Message;
	}
	
	public List<String> getWord() {
		return Word;
	}
}
