package factory;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import scraper.MangaHubList;

public class MangaHubListFactory extends BaseFactory {
	private final String website = "https://mangahub.io/";
	
	public void scrape(int startingPoint, int increment, String type) {
		scrape(startingPoint, increment, type, website + "search/page/", new MangaHubList());
	}
	
	public boolean checkNextPage(String link) {
		try {
			Document html = Jsoup.connect(link).userAgent("Mozilla").get();
			
			Element mangaList = html.selectFirst("div.row");
			if(! mangaList.text().equalsIgnoreCase("no manga found!")) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			checkNextPage(link);
		}
		
		return false;
	}
}
