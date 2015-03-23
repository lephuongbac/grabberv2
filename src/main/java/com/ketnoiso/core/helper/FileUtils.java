package com.ketnoiso.core.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * The Class FileUtils.
 */
public class FileUtils {
	
	/** The Constant TEMP_DIR. */
	public final static String TEMP_DIR = "/opt/tomcat/webapps/ROOT/download/";
	//public final static String TEMP_DIR = "c:/";
	/**
	 * Zip file.
	 * 
	 * @param fileIn
	 *            the file in
	 * @return the file
	 * @throws java.io.FileNotFoundException
	 *             the file not found exception
	 */
	public File zipFile(File fileIn) throws FileNotFoundException
    {
    	byte[] buffer = new byte[1024];
    	String zipFileName = changeExtension(fileIn.getName(),".zip");
    	File fileOut = null;
    	try{
    		fileOut =new File(TEMP_DIR,zipFileName);
    		FileOutputStream fos = new FileOutputStream(fileOut);
    		FileInputStream in = new FileInputStream(fileIn);
    		ZipOutputStream zos = new ZipOutputStream(fos);
    		ZipEntry ze= new ZipEntry(fileIn.getName());
    		zos.putNextEntry(ze);
    		int len;
    		while ((len = in.read(buffer)) > 0) {
    			zos.write(buffer, 0, len);
    		}
    		in.close();
    		zos.closeEntry();
    		//remember close it
    		zos.close();
    	}catch(Exception ex){
    	   ex.printStackTrace();
    	}
		return fileOut;
    }
	
	/**
	 * Change extension.
	 * 
	 * @param originalName
	 *            the original name
	 * @param newExtension
	 *            the new extension
	 * @return the string
	 */
	static String changeExtension(String originalName, String newExtension) {
	    int lastDot = originalName.lastIndexOf(".");
	    if (lastDot != -1) {
	        return originalName.substring(0, lastDot) + newExtension;
	    } else {
	        return originalName + newExtension;
	    }
	}
}
