package com.ketnoiso.media.grabber.parser.zing;


import com.ketnoiso.core.helper.ZingParser;
import com.ketnoiso.media.grabber.core.model.Article;
import com.ketnoiso.media.grabber.core.model.Playlist;
import com.ketnoiso.media.grabber.core.model.PlaylistInfo;
import com.ketnoiso.media.grabber.services.ArticleManager;
import com.ketnoiso.media.grabber.model.*;
import com.ketnoiso.media.grabber.parser.MediaParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class ZingMediaParser.
 */
@Service(value = "zingMediaParser")
public class ZingMediaParser implements MediaParser {

	/** The log. */
	private Logger LOG = Logger.getLogger(getClass());

	/** The context. */
	@Autowired
	ServletContext context;

	/** The article manager. */
	@Autowired
	private ArticleManager articleManager;

	/** The driver. */
	@Autowired
	private WebDriver driver;

	/** The mail manager. */
	/*@Autowired
	private MailManager mailManager;*/

	/** The file location. */
	@Value("${file.location}")
	private String fileLocation;

    @Value("${zing.url}")
    private String zingUrl;

	/** The timeout millis. */
	private int timeoutMillis = 5000;

	/** The doc. */
	private Document doc;

	/**
	 * Process page.
	 * 
	 * @param playlistIdentider
	 *            the playlist identider
	 * @return the string
	 * @throws java.net.MalformedURLException
	 *             the malformed url exception
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private String processPage(String playlistIdentider)
			throws MalformedURLException, IOException {
		/*String linkAlbum = "http://app.ketnoiso.net/album/zingalbum/"
				+ playlistIdentider + ".html?st=0";*/
		String linkAlbum = zingUrl + "/album/DownloadAlbum/"
				+ playlistIdentider + ".html?st=0";
		doc = Jsoup.parse(new URL(linkAlbum), timeoutMillis);
		return linkAlbum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.baclp.com.ketnoiso.media.grabber.parser.MediaParser#parser(java.lang.String,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Playlist parser(String playlistIdentider,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Playlist playlistDecorator = new Playlist();
		try {
			String linkAlbum = processPage(playlistIdentider);
			playlistDecorator = getAlbumInfo(linkAlbum);
			List<Item> items = getMp3Item(playlistDecorator, request, response);
			List<Article> articles = new ArrayList<Article>();
			for (Item item : items) {
				Article articleDecorator = articleManager
						.getArticleDecorator(item, playlistDecorator);
				request.setAttribute("albumTitle", playlistDecorator.getTitle());
				String link = request.getContextPath() + "/Downloader?f="
						+ articleDecorator.getSongId() + "&albumId="
						+ playlistDecorator.getPlaylistId() + "&fileName="
						+ articleDecorator.getFileName();
				articleDecorator.setDownloadLink(link);
				articles.add(articleDecorator);
			}
			playlistDecorator.setArticles(articles);

			PlaylistInfo playlistInfo = parserPlaylistInfo(playlistIdentider,
					request, response);
			playlistDecorator.setPlaylistInfo(playlistInfo);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playlistDecorator;
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
	 * Get playlist xml url, albumId.
	 * 
	 * @param linkAlbum
	 *            the link album
	 * @return the album info
	 */
	public Playlist getAlbumInfo(String linkAlbum) {
		Playlist albumDecorator = new Playlist();
		try {

			String xmlUrl = "";
			URL url = new URL(linkAlbum);
			/*
			 * InputStream stream = url.openStream(); BufferedReader br = new
			 * BufferedReader(new InputStreamReader(stream));
			 * 
			 * StringBuilder sb = new StringBuilder();
			 * 
			 * String line; while ((line = br.readLine()) != null) {
			 * sb.append(line); }
			 */
			StringBuilder sb = new StringBuilder();
			sb = toString(linkAlbum);
			Pattern playlistPattern = Pattern.compile("xmlURL=(.*?)&amp");
			Matcher m = playlistPattern.matcher(sb.toString());
			String tmpUrl = "";
			while (m.find()) {
				tmpUrl = m.group(1);
				// s now contains "BAR"
			}
			String strAlbum[] = tmpUrl.split("&");
			if (strAlbum.length > 0) {
				xmlUrl = strAlbum[0];
			}
			Pattern titlePattern = Pattern.compile("<title>(.*?)</title>");
			Matcher mTitle = titlePattern.matcher(sb.toString());
			while (mTitle.find()) {
				albumDecorator.setTitle(mTitle.group(1));
				// s now contains "BAR"
			}
			//
            xmlUrl = xmlUrl.replace("http://mp3.zing.vn", zingUrl);
			LOG.info("xmlUrl" + xmlUrl);
			albumDecorator.setPlaylistUrl(xmlUrl);
			albumDecorator.setPlaylistId(extractAlbumId(linkAlbum));
			if (StringUtils.isEmpty(xmlUrl)) {
				/*mailManager.sendMail("Admin", linkAlbum);*/
				LOG.error("There are some changes in source code, can't find playlist xml.");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return albumDecorator;
	}

	/**
	 * Download playlist xml file then parser to get mp3 item.
	 * 
	 * @param albumDecorator
	 *            the album decorator
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the mp3 item
	 */
	public ArrayList<Item> getMp3Item(Playlist albumDecorator,
			HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Item> mp3Items = new ArrayList<Item>();
		try {
			String playlistUrl = albumDecorator.getPlaylistUrl();
			if (playlistUrl != null) {
				URL google = new URL(playlistUrl);
				String playlistId = albumDecorator.getPlaylistId();
				File savePathFile = new File(new File(context.getRealPath(""),
						fileLocation), playlistId);
				if (!savePathFile.exists()) {
					savePathFile.mkdir();
				}
				File fileOut = new File(savePathFile, URLDecoder.decode(
						new File(google.getFile()).getName(), "UTF-8"));
				StringBuilder sb = new StringBuilder();
				sb = toString(playlistUrl);
				FileUtils.writeStringToFile(fileOut, sb.toString(), "UTF-8");
				/* FileUtils.copyURLToFile(google, fileOut); */
				ZingParser zingParser = new ZingParser();
				Data data = zingParser.getDataFromXML(fileOut);
				for (Item item : data.getItems()) {
					mp3Items.add(item);
				}
			}
		} catch (MalformedURLException e) {
			LOG.error(e);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
		return mp3Items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.baclp.com.ketnoiso.media.grabber.parser.MediaParser#parserPlaylistInfo(java.lang.String,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public PlaylistInfo parserPlaylistInfo(String playlistIdentider,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		PlaylistInfo playlistInfo = new PlaylistInfo();
		try {
			Elements elAlbumInfos = doc.select(".album-info p");
			if (elAlbumInfos != null && !elAlbumInfos.isEmpty()) {
				int i = 0;
				for (Element element : elAlbumInfos) {
					try {
						String allText = element.text();
						String key = element.getElementsByTag("span").text();
						String val = StringUtils.removeStart(allText, key)
								.trim();
						if (i == 1) {
							playlistInfo.setPublishedYear(Integer.valueOf(val));
						}
						i++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			Elements elPlaylistDesc = doc.select("p#_albumIntro");
			if (elPlaylistDesc != null && !elPlaylistDesc.isEmpty()) {
				String strPlaylistDesc = elPlaylistDesc.get(0).text();
				playlistInfo.setDescription(strPlaylistDesc);
			}

			Element elPlaylistCover = doc.select(".pthumb")
					.get(0);
			String strPlaylistCover = elPlaylistCover.attr("src");
			playlistInfo.setPlaylistImageCover(strPlaylistCover);
		} catch (NumberFormatException e) {
			LOG.error(e);
		}
		return playlistInfo;
	}

	/**
	 * To string.
	 * 
	 * @param url
	 *            the url
	 * @return the string builder
	 */
	public StringBuilder toString(String url) {
		driver.get(url);
		StringBuilder sb = new StringBuilder(driver.getPageSource());
		return sb;
	}
}
