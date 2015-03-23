package com.ketnoiso.media.grabber.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URL;

/**
 * The Class DownloaderController.
 */
@Controller
public class DownloaderController {
    @Value("${zing.url}")
    private String zingUrl;
	
	/** The zing server. */
	//String ZING_SERVER = "http://app.ketnoiso.net/xml/load-song/" ;

	/** The Constant DOWNLOAD_CONNECTION_TIMEOUT. */
	private static final int DOWNLOAD_CONNECTION_TIMEOUT = 600000;
	
	/** The Constant DOWNLOAD_READ_TIMEOUT. */
	private static final int DOWNLOAD_READ_TIMEOUT = 600000;
	
	/** The context. */
	@Autowired
	ServletContext context;
	
	/** The file location. */
	@Value("${file.location}")
	private String fileLocation;
	
	/**
	 * Handle zing mp3.
	 * 
	 * @param download
	 *            the download
	 * @param albumId
	 *            the album id
	 * @param fileName
	 *            the file name
	 * @param prefix
	 *            the prefix
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping("/Downloader")
	@ResponseBody
	public String handleZingMp3(
			@RequestParam(required = false) String download,
			@RequestParam(required = false) String albumId,
			@RequestParam(required = false) String fileName,
			@RequestParam(value = "f", required = false) String prefix,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap model = new ModelMap();
        String ZING_SERVER = zingUrl + "/xml/load-song/" ;
		String downloadUrl = ZING_SERVER + prefix;
		URL stream = new URL(downloadUrl);
		String subFolder = albumId;
		File savePath = new File(fileLocation, subFolder);
		if(!savePath.exists()) {
			savePath.mkdir();
		}
		File fileOut = new File(savePath,fileName);
		if(!fileOut.exists()) {
			FileUtils.copyURLToFile(stream, fileOut, DOWNLOAD_CONNECTION_TIMEOUT, DOWNLOAD_READ_TIMEOUT);
		}
		
		String url = request.getContextPath() + "/download/" + subFolder + "/" + fileName;
		
		if(download != null && download.equals("1")) {
			response.sendRedirect(request.getContextPath() + "/download/" + subFolder + "/" + fileName);
		}
		return url;
	}
}
