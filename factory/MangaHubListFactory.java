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
	private final String website = "https://mangahub.io/";
	private int startingPoint;
	private int increment;
	private String saveType;
	
	public MangaHubListFactory() {}
	
	public MangaHubListFactory(int start, int inc, String type) {
		this.startingPoint = start;
		this.increment = inc;
		this.saveType = type;
	}
	
	public MangaHubResponse scrape() {
		return this.createList(this.website, 1761, 1);
	}
	
	public MangaHubResponse scrape(int startingPoint, int increment) {
		return this.createList(this.website, startingPoint, increment);
	}
	
	public MangaHubResponse createList(String website, int startingPoint, int increment) {
		MangaHubList site = new MangaHubList();
		MangaHubResponse response = new MangaHubResponse();
		
		int i = 1762;
		String link = website + "search/page/" + i + "?q=&order=ALPHABET&genre=all";
		do {
			print("[MangaHub] Page: " + i);
			for(Manga manga : site.scrape(link)) {
				response.add(manga);
			}
			i += 1;
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
		switch(this.saveType) {
			case "text":
				scrape(this.startingPoint, this.increment).toText();
				break;
			case "json":
				scrape(this.startingPoint, this.increment).toText();
				break;
			case "mysql":
				scrape(this.startingPoint, this.increment).toText();
				break;
			case "xml":
				scrape(this.startingPoint, this.increment).toText();
				break;
			default:
				print("Data type not supported!");
				break;
		}
	}
}
