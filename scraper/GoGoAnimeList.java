package main.scraper;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoGoAnimeList extends BaseScraper{
	public ArrayList<String> scrape(String website) {
		ArrayList<String> links = new ArrayList<String>();
		
		try {
			Document html = Jsoup.connect(website).get();
			
			Element animeList = html.selectFirst("div.anime_list_body");
			Element list = animeList.selectFirst("ul.listing");
			Elements animes = list.select("li");
			
			for(Element anime : animes) {
				links.add(anime.selectFirst("a").attr("href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return links;
	}
}
