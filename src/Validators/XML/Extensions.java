package Validators.XML;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Extensions {
	
	private List<Extension> listOfExtensions;

	public void setListOfExtensions(List<Extension> listOfExtensions) {
		this.listOfExtensions = listOfExtensions;
	}
	
	@XmlElementWrapper(name ="listOfExtensions")
	@XmlElement(name = "Extension")
	public List<Extension> getListOfExtensions() {
		return listOfExtensions;
	}

}
