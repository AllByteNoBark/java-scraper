package main.factory;

import static main.util.Utility.print;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import main.responses.GoGoAnimeResponse;
import main.scraper.GoGoAnimeList;

public class GoGoAnimeListFactory {
	private final String website = "https://gogoanime3.net/";
	
	public void scrape(int startingPoint, int increment, String type) {
		GoGoAnimeList site = new GoGoAnimeList();
		int i = startingPoint;
		String listLink = website + "anime-list.html?page=" + i;
		do {
			print("[GoGoAnime] Page: " + i);
			saveResponse(site.scrape(listLink), type);
			
			i += increment;
			listLink = website + "anime-list.html?page=" + i;
		} while(checkNextPage(listLink));
	}
	
	private boolean checkNextPage(String website) {
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
			e.printStackTrace();
		}
		
		return false;
	}
	
	private void saveResponse(GoGoAnimeResponse instance, String type) {
		switch(type.toLowerCase()) {
			case "text":
				instance.toText();
				break;
			case "json":
				instance.toJSON();
				break;
			case "mysql":
				instance.toText();
				break;
			case "xml":
				instance.toText();
				break;
			default:
				print("Data type not supported!");
				break;
		}
	}
}
