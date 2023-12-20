package main;

import main.factory.GoGoAnimeListFactory;
import main.scraper.GoGoAnime;

public class Main {

	public static void main(String[] args) {
		GoGoAnime site = new GoGoAnime();
		GoGoAnimeListFactory a = new GoGoAnimeListFactory();
		
		// site.scrape("https://gogoanime3.net/category/monster");
		a.createList("https://gogoanime3.net/anime-list.html");
	}

}
