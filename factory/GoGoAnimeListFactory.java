package main.factory;

import static main.util.Utility.print;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import main.object.Anime;
import main.responses.GoGoAnimeResponse;
import main.scraper.GoGoAnimeList;

public class GoGoAnimeListFactory extends Thread{
	private int startingPoint;
	private int increment;
	
	public GoGoAnimeListFactory() {}
	
	public GoGoAnimeListFactory(int start, int inc) {
		this.startingPoint = start;
		this.increment = inc;
	}
	
	public GoGoAnimeResponse scrape(String website) {
		return this.createList(website, 95, 1);
	}
	
	public GoGoAnimeResponse scrape(String website, int startingPoint, int increment) {
		return this.createList(website, startingPoint, increment);
	}
	
	public GoGoAnimeResponse createList(String website, int startingPoint, int increment) {
		GoGoAnimeList site = new GoGoAnimeList();
		GoGoAnimeResponse response = new GoGoAnimeResponse();
		
		int i = startingPoint;
		String listLink = website + "anime-list.html?page=" + i;
		do {
			print("[GoGoAnime] Page: " + i);
			listLink = website + "anime-list.html?page=" + i;
			for(Anime anime : site.scrape(listLink)) {
				response.add(anime);
			}
			i += increment;
			listLink = website + "anime-list.html?page=" + i;
		} while(checkNextPage(listLink));
		
		return response;
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
	
	public void run() {
		createList("https://gogoanime3.net/", this.startingPoint, this.increment);
	}
}
