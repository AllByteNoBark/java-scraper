package main.factory;

import java.io.IOException;
import java.util.ArrayList;

import main.responses.GoGoAnimeResponse;
import main.scraper.GoGoAnimeList;

public class GoGoAnimeListFactory {
	public void createList(String website) {
		ArrayList<String> links = new ArrayList<String>();
		GoGoAnimeList site = new GoGoAnimeList();
		for(int i = 1; i <= 5; i++) {
			for(String link : site.scrape(website + "?page=" + i)) {
				links.add("https://gogoanime3.net" + link);
			}
		}
		
		GoGoAnimeResponse response = new GoGoAnimeResponse();
		response.listResponse(links);
	}
}
