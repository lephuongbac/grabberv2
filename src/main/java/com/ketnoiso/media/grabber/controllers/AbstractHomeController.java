package com.ketnoiso.media.grabber.controllers;

import com.ketnoiso.media.grabber.core.model.Playlist;
import com.ketnoiso.core.helper.ZingParser;
import com.ketnoiso.media.grabber.model.Data;
import com.ketnoiso.media.grabber.model.Item;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class AbstractHomeController.
 */
public abstract class AbstractHomeController {
	
	/**
	 * Extract album id.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 */
	protected String extractAlbumId(String str) {
		String albumId = null;
		Pattern playlistPattern = Pattern.compile("/(.*?).html");
		Matcher m = playlistPattern.matcher(str);
		while (m.find()) {
			albumId = m.group(1);
			String albumIds[] = albumId.split("/");
			for (String string : albumIds) {
				albumId = string;
			}
		    // s now contains "BAR"
		}
		return albumId;
	}
	
	/**
	 * Get AlbumDecorator from album url.
	 * 
	 * @param linkAlbum
	 *            the link album
	 * @return the album decorator
	 */
	public Playlist getAlbumDecorator(String linkAlbum) {
		Playlist albumDecorator = new Playlist();
		try {
			String xmlUrl = "";
			URL url = new URL(
					linkAlbum);
			InputStream stream = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));

			StringBuilder sb = new StringBuilder();

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			Pattern playlistPattern = Pattern.compile("xmlURL=(.*?)\"");

			Matcher m = playlistPattern.matcher(sb.toString());
			String tmpUrl = "";
			while (m.find()) {
				tmpUrl = m.group(1);
			    // s now contains "BAR"
			}
			String strAlbum[] = tmpUrl.split("&amp;");
			if(strAlbum.length > 0) {
				xmlUrl = strAlbum[0];
			}
			Pattern titlePattern = Pattern.compile("<title>(.*?)</title>");
			Matcher mTitle = titlePattern.matcher(sb.toString());
			while (mTitle.find()) {
				albumDecorator.setTitle(mTitle.group(1));
			    // s now contains "BAR"
			}
			albumDecorator.setPlaylistUrl(xmlUrl);
			albumDecorator.setPlaylistId(extractAlbumId(linkAlbum));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albumDecorator;
	}
	
	/**
	 * Gets the mp3 item.
	 * 
	 * @param albumDecorator
	 *            the album decorator
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the mp3 item
	 */
	public ArrayList<Item> getMp3Item(Playlist albumDecorator,HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Item> mp3Items = new ArrayList<Item>();
		try {
			String playlistUrl = albumDecorator.getPlaylistUrl();
			if(playlistUrl != null) {
				URL google = new URL(playlistUrl);
				String subFolder = albumDecorator.getPlaylistId();
				String savePath = request.getSession().getServletContext().getRealPath("download") + File.separator + subFolder;
				File savePathFile = new File(savePath);
				if(!savePathFile.exists()) {
					savePathFile.mkdir();
				}
				File fileOut = new File(savePath,URLDecoder.decode(new File(google.getFile()).getName(), "UTF-8"));
				FileUtils.copyURLToFile(google, fileOut);
				ZingParser zingParser = new ZingParser();
				Data data = zingParser.getDataFromXML(fileOut);
				for(Item item:data.getItems()) {
					mp3Items.add(item);
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mp3Items;
	}
}
