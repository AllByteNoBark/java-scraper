package main.scraper;

import java.io.IOException;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.object.Manga;

public class MangaHubList extends BaseScraper {
	public ArrayList<Manga> scrape(String website) {
		ArrayList<Manga> links = new ArrayList<Manga>();
		MangaHub scraper = new MangaHub();
		
		try {
			Document html = Jsoup.connect(website)
					.userAgent("Mozilla")
					.get();
			
			Element mangaList = html.selectFirst("div.row");
			Elements mangas = mangaList.select("div._1KYcM");
			
			for(Element manga : mangas) {
				links.add(scraper.scrape(manga.select("div > div.media-manga > div.media-left > a").attr("href")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return links;
	}
}
