package hu.innobyte.service;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "param")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@Builder
public class InitParams {
	private String userName;
	private String password;
	private String host;
	private String port;
	private String databaseName;
	
	public static InitParams loadFromFile(String path) {
		InitParams initParams = null;
		
		try {
    		JAXBContext jaxbContext = JAXBContext.newInstance(InitParams.class);
    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		
    		initParams = (InitParams) jaxbUnmarshaller.unmarshal(new File(path));

		} catch (Exception e) {
			System.err.println(String.format("Error in XML load, exception: %s, message: %s",e.getClass(),e.getMessage()));
		}
		return initParams;
	}
}
