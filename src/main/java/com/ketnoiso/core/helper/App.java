package com.ketnoiso.core.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class App.
 */
public class App {
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		App app = new App();
		String str = app.extractSongId("http://mp3.zing.vn/xml/load-song/MjAxMSUyRjA2JTJGMDYlMkY0JTJGMyUyRjQzMjhkZDExNmY4ZjJlMDlhNDVjMTA5OWE1NGZhY2MyLm1wMyU3QzI=");
		str = app.extractAlbumId("http://mp3.zing.vn/album/Tim-Lai-Bau-Troi-Tuan-Hung/ZWZ9E89F.html");
		System.out.println(str);
		
		/*
		 * ArrayList<Item> items = new ArrayList<Item>(); Item item = new
		 * Item(); item.setLink("http://www.google.com");
		 * item.setHq("Vip only"); item.setPerformer("Bruno Mars");
		 * item.setSource("http://download"); item.setTitle("sasa"); Item item2
		 * = new Item(); item2.setLink("http://www.google.com");
		 * item2.setLink("http://www.google.com"); item2.setHq("Vip only");
		 * item2.setPerformer("Bruno Mars"); item2.setSource("http://download");
		 * item2.setTitle("sasa"); items.add(item); items.add(item2); Data data
		 * = new Data(); data.setItems(items); try { JAXBContext context =
		 * JAXBContext.newInstance(Data.class); Marshaller ma =
		 * context.createMarshaller(); Writer w = null; try { w = new
		 * FileWriter("test.xml"); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } ma.marshal(data,
		 * w); Unmarshaller un = context.createUnmarshaller(); Data dataun =
		 * (Data) un.unmarshal(new File("test.xml")); System.out.println("a");
		 * 
		 * } catch (JAXBException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}
	
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
	 * Extract song id.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 */
	protected String extractSongId(String str) {
		String songId = null;
		Pattern playlistPattern = Pattern.compile("load-song/(.*?)\\z");
		Matcher m = playlistPattern.matcher(str);
		while (m.find()) {
			songId = m.group(1);
		    // s now contains "BAR"
		}
		return songId;
	}
}
