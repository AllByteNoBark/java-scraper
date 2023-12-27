package main.scraper;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.object.Manga;
import static main.util.Utility.print;
public class MangaHub extends BaseScraper{
	public Manga scrape(String website) {
		String name =  "";
		String summary = "";
		String author = "";
		String artist = "";
		String status = "";
		
		try {
			print(website);
			Document html = Jsoup.connect(website)
					.userAgent("Mozilla")
					.get();
			
			Element mangaInfo = html.selectFirst("section._2fecr > div.container-fluid > div.row > div._3owCZ");
			
			if(mangaInfo == null) return new Manga(name, summary, author, artist, status);
		
			Element fullNames = mangaInfo.selectFirst("h1");
			name = fullNames.text().substring(0, fullNames.text().length() - fullNames.selectFirst("small").text().length());
			if(mangaInfo.selectFirst("h1:has(a)") != null) {
				name = name.substring(0, name.length() - fullNames.selectFirst("a").text().length());
			}
			
			Elements extraInfo = mangaInfo.selectFirst("div > div > div > div").select("div:lt(3)");
			
			for(Element info : extraInfo) {
				String span = info.selectFirst("span._3SlhO").text();
				String content = info.text().substring(span.length(), info.text().length());
				switch(span.toLowerCase()) {
					case "author":
						author = content;
						break;
					case "artist":
						artist = content;
						break;
					case "status":
						status = content;
						break;
					default:
						break;
				}
			}
			
			Elements sumup = html.select("section:eq(2) > div.container-fluid > div.row > div.col-xs-12 > div._2wcqV > div.tab-content > div#chapters-tab-pane-999 > div > p");
			summary = sumup.text();
		} catch (IOException e) {
			print("[Error]: " + e.getMessage());
			if(e.getClass() == SocketTimeoutException.class) {
				scrape(website);
			}
		}
		
		return new Manga(name, summary, author, artist, status);
	}
}
