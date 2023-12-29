package scraper;

import static util.Utility.print;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import saver.MangaHubSaver;

public class MangaHubList extends BaseListScraper {
	public MangaHubSaver scrape(String website) {
		MangaHubSaver response = new MangaHubSaver();
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
			print("[Error]: " + e.getMessage() + " [web]: " + website);
			print("Retrying.");
			scrape(website);
		}
		
		return response;
	}
}
