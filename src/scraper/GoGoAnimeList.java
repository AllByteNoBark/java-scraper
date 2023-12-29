package scraper;

import static util.Utility.print;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import saver.GoGoAnimeSaver;

public class GoGoAnimeList extends BaseListScraper{
	public GoGoAnimeSaver scrape(String website) {
		GoGoAnimeSaver response = new GoGoAnimeSaver();
		GoGoAnime scraper = new GoGoAnime();
		
		try {
			Document html = Jsoup.connect(website).get();
			
			Element animeList = html.selectFirst("div.anime_list_body");
			Element list = animeList.selectFirst("ul.listing");
			Elements animes = list.select("li");
			
			for(Element anime : animes) {
				response.add(scraper.scrape(anime.selectFirst("a").attr("href")));
			}
		} catch (IOException e) {
			print("[Error]: " + e.getMessage() + " [web]: " + website);
			print("Retrying.");
			scrape(website);
		}
		
		return response;
	}
}
