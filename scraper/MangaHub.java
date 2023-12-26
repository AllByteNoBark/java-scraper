package main.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.object.Manga;

public class MangaHub extends BaseScraper{
	public Manga scrape(String website) {
		Manga manga = null;
		String name = null;
		String summary = null;
		String author = null;
		String artist = null;
		String status = null;
		
		try {
			Document html = Jsoup.connect(website)
					.userAgent("Mozilla")
					.get();
			
			Element mangaInfo = html.selectFirst("section._2fecr > div.container-fluid > div.row > div._3owCZ");
		
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
			
			manga = new Manga(name, summary, author, artist, status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return manga;
	}
}
