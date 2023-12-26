package main.factory;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import main.object.Manga;
import main.responses.MangaHubResponse;
import main.scraper.MangaHubList;

import static main.util.Utility.print;

public class MangaHubListFactory extends Thread{
	private int startingPoint;
	private int increment;
	
	public MangaHubListFactory() {}
	
	public MangaHubListFactory(int start, int inc) {
		this.startingPoint = start;
		this.increment = inc;
	}
	
	public MangaHubResponse scrape(String website) {
		return this.createList(website, 1761, 1);
	}
	
	public MangaHubResponse scrape(String website, int startingPoint, int increment) {
		return this.createList(website, startingPoint, increment);
	}
	
	public MangaHubResponse createList(String website, int startingPoint, int increment) {
		MangaHubList site = new MangaHubList();
		MangaHubResponse response = new MangaHubResponse();
		
		int i = startingPoint;
		String link = website + "search/page/" + i + "?q=&order=ALPHABET&genre=all";
		do {
			print("[MangaHub] Page: " + i);
			for(Manga manga : site.scrape(link)) {
				response.add(manga);
			}
			i += increment;
			link = website + "search/page/" + i + "?q=&order=ALPHABET&genre=all";
		} while(checkNextPage(link));
		
		return response;
	}
	
	private boolean checkNextPage(String link) {
		try {
			Document html = Jsoup.connect(link)
					.userAgent("Mozilla")
					.get();
			
			Element mangaList = html.selectFirst("div.row");
			if(! mangaList.text().equalsIgnoreCase("no manga found!")) {
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
		scrape("https://mangahub.io/", this.startingPoint, this.increment);
	}
}
