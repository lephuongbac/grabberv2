package com.ketnoiso.core.helper;

import com.ketnoiso.media.grabber.model.Data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * The Class ZingParser.
 */
public class ZingParser {
	
	/**
	 * Gets the data from xml.
	 * 
	 * @param srcFile
	 *            the src file
	 * @return the data from xml
	 */
	public Data getDataFromXML(File srcFile) {
		JAXBContext context = null;
		try {
			context = JAXBContext.newInstance(Data.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data data = null;
		try {
			Unmarshaller un = context.createUnmarshaller();
			data = (Data) un.unmarshal(srcFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
