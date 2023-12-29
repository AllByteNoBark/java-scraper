package factory;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import scraper.GoGoAnimeList;

public class GoGoAnimeListFactory extends BaseFactory {
	private final String website = "https://gogoanime3.net/";
	
	public void scrape(int startingPoint, int increment, String type) {
		scrape(startingPoint, increment, type, website + "anime-list.html?page=", new GoGoAnimeList());
	}
	
	public boolean checkNextPage(String website) {
		try {
			Document html = Jsoup.connect(website).get();
			
			Element animeList = html.selectFirst("div.anime_list_body");
			Element list = animeList.selectFirst("ul.listing");
			if(list.selectFirst("li") != null) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			checkNextPage(website);
		}
		
		return false;
	}
}
