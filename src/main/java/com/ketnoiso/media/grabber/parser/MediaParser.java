package com.ketnoiso.media.grabber.parser;

import com.ketnoiso.media.grabber.model.PlaylistDecorator;
import com.ketnoiso.media.grabber.model.PlaylistInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Interface MediaParser.
 */
public interface MediaParser {
	
	/**
	 * Parser.
	 * 
	 * @param playlistIdentider
	 *            the playlist identider
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the playlist decorator
	 */
	public PlaylistDecorator parser(String playlistIdentider, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Parser playlist info.
	 * 
	 * @param playlistIdentider
	 *            the playlist identider
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the playlist info
	 */
	public PlaylistInfo parserPlaylistInfo(String playlistIdentider, HttpServletRequest request, HttpServletResponse response);
}
