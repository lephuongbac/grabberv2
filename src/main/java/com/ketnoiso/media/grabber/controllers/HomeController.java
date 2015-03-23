package com.ketnoiso.media.grabber.controllers;

import com.ketnoiso.media.grabber.model.PlaylistDecorator;
import com.ketnoiso.media.grabber.services.ArticleManager;
import com.ketnoiso.media.grabber.parser.MediaParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class HomeController.
 * 
 * @author Phong
 */
@RestController("api")
public class HomeController extends AbstractHomeController {
	
	/** The context. */
	@Autowired
	ServletContext context;
	
	/** The article manager. */
	@Autowired
	private ArticleManager articleManager;
	
	/** Spring bean factory. */
	@Autowired
	private BeanFactory beanFactory;
	
	/** The Constant SESSION_PLAYLIST_DECORATOR_ID. */
	public static final String SESSION_PLAYLIST_DECORATOR_ID = "playlist";
	
	/** The Constant PLAYLIST_DATA_FILENAME. */
	public static final String PLAYLIST_DATA_FILENAME = "pldata.xml";
	
	
	/** Downloaded file location. */
	@Value("${file.location}")
	private String fileLocation;
	

	/**
	 * Handle zing mp3.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping("/ZingMp3")
	public ModelAndView handleZingMp3(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("redirect:/Play");
	}
	
	/**
	 * Handle zing mp3.
	 * 
	 * @param albumId
	 *            the album id
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value="/Play", method= RequestMethod.GET)
	public ModelAndView handleZingMp3(@RequestParam(required=false) String albumId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap model = new ModelMap();
		HashMap<String,PlaylistDecorator> playLists;
		Object obj = request.getSession().getAttribute(HomeController.SESSION_PLAYLIST_DECORATOR_ID);
		if(obj == null) {
			playLists = new HashMap<String,PlaylistDecorator>(); 
		} else {
			playLists = (HashMap<String,PlaylistDecorator>) obj;
		}
		
		if(!StringUtils.isEmpty(albumId)) {
			PlaylistDecorator playlistDecorator = null;
			File savePathFile = new File(new File(context.getRealPath(""),fileLocation), albumId);
			File playlistXmlData = new File(savePathFile, PLAYLIST_DATA_FILENAME);
			if(playlistXmlData.exists() && playlistXmlData.isFile()) {
				JAXBContext  context = JAXBContext.newInstance(PlaylistDecorator.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				
				InputStream inputStream = new FileInputStream(playlistXmlData);
				Reader reader = new InputStreamReader(inputStream, "UTF-8");
				
				playlistDecorator = (PlaylistDecorator) unmarshaller.unmarshal(reader);
			} else {
				MediaParser parser = (MediaParser) beanFactory.getBean("zingMediaParser");
				playlistDecorator = parser.parser(albumId, request, response);
				
				JAXBContext  context = JAXBContext.newInstance(PlaylistDecorator.class);
				Marshaller marshaller = context.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(playlistDecorator, playlistXmlData);
			}
			
			playLists.put(playlistDecorator.getPlaylistId(), playlistDecorator);
			model.addAttribute("playlistDecorator", playlistDecorator);
			model.addAttribute("title", playlistDecorator.getTitle());
		}
		request.getSession().setAttribute(SESSION_PLAYLIST_DECORATOR_ID, playLists);
		model.addAttribute("playlists", playLists);
		/*RequestDispatcher rd = request.getRequestDispatcher("/jsp/zing.jsp");
		rd.forward(request, response);*/
		return new ModelAndView("zing", model);
	}
	
	/**
	 * Do post.
	 * 
	 * @param fileUrl
	 *            the file url
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 * @throws javax.servlet.ServletException
	 *             the servlet exception
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws javax.xml.bind.JAXBException
	 *             the JAXB exception
	 */
	@RequestMapping(value="/grab")
	protected PlaylistDecorator doPost(@RequestParam String fileUrl,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JAXBException {
		// TODO Auto-generated method stub
		
		String linkAlbum = fileUrl;
		URL urlLinkAlbum = new URL(linkAlbum);
		if(StringUtils.contains(urlLinkAlbum.getAuthority(), "google.com")) {
			Map<String, String> querys = getQueryMap(urlLinkAlbum.getQuery());
			linkAlbum = querys.get("q");
		}
		String albumId = extractAlbumId(linkAlbum);
		
		String json = null;
		HashMap<String,PlaylistDecorator> playLists;
		Object obj = request.getSession().getAttribute(HomeController.SESSION_PLAYLIST_DECORATOR_ID);
		if(obj == null) {
			playLists = new HashMap<String,PlaylistDecorator>(); 
		} else {
			playLists = (HashMap<String,PlaylistDecorator>) obj;
		}
		
		PlaylistDecorator playlistDecorator = null;
		if(!StringUtils.isEmpty(albumId)) {
			File savePathFile = new File(new File(context.getRealPath(""),fileLocation), albumId);
			if(!savePathFile.exists()) {	
				savePathFile.mkdir();
			}
			File playlistXmlData = new File(savePathFile, PLAYLIST_DATA_FILENAME);
			if(playlistXmlData.exists() && playlistXmlData.isFile()) {
				JAXBContext  context = JAXBContext.newInstance(PlaylistDecorator.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				
				InputStream inputStream = new FileInputStream(playlistXmlData);
				Reader reader = new InputStreamReader(inputStream, "UTF-8");
				
				playlistDecorator = (PlaylistDecorator) unmarshaller.unmarshal(reader);
			} else {
				MediaParser parser = (MediaParser) beanFactory.getBean("zingMediaParser");
				playlistDecorator = parser.parser(albumId, request, response);
				
				JAXBContext  context = JAXBContext.newInstance(PlaylistDecorator.class);
				Marshaller marshaller = context.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(playlistDecorator, playlistXmlData);
			}
			
			playLists.put(playlistDecorator.getPlaylistId(), playlistDecorator);
		}
		request.getSession().setAttribute(SESSION_PLAYLIST_DECORATOR_ID, playLists);
		return playlistDecorator;
	}
	
	/**
	 * Handle zing mp3 post.
	 * 
	 * @param fileUrl
	 *            the file url
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the model and view
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value="/Play", method= RequestMethod.POST)
	public ModelAndView handleZingMp3Post(@RequestParam String fileUrl, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String linkAlbum = fileUrl;
		URL urlLinkAlbum = new URL(linkAlbum);
		if(StringUtils.contains(urlLinkAlbum.getAuthority(), "google.com")) {
			Map<String, String> querys = getQueryMap(urlLinkAlbum.getQuery());
			linkAlbum = querys.get("q");
		}
		String albumId = extractAlbumId(linkAlbum);
		String link = request.getContextPath() + "/ZingMp3" + "?albumId=" + albumId;
		return new ModelAndView("redirect:"+link);
	}
	
	/**
	 * Gets the query map.
	 * 
	 * @param query
	 *            the query
	 * @return the query map
	 */
	public static Map<String, String> getQueryMap(String query)  
	{  
	    String[] params = query.split("&");  
	    Map<String, String> map = new HashMap<String, String>();  
	    for (String param : params)  
	    {  
	        String name = param.split("=")[0];  
	        String value = param.split("=")[1];  
	        map.put(name, value);  
	    }  
	    return map;  
	}
}
