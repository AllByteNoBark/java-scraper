package main.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.responses.MangaHubResponse;

public class MangaHubList extends BaseScraper {
	public MangaHubResponse scrape(String website) {
		MangaHubResponse response = new MangaHubResponse();
		MangaHub scraper = new MangaHub();
		
		try {
			Document html = Jsoup.connect(website)
					.userAgent("Mozilla")
					.get();
			
			Element mangaList = html.selectFirst("div.row");
			Elements mangas = mangaList.select("div._1KYcM");
			
			for(Element manga : mangas) {
				response.add(scraper.scrape(manga.select("div > div.media-manga > div.media-left > a").attr("href")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
}
