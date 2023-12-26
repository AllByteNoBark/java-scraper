package main.scraper;

import java.io.IOException;
import static main.util.Utility.print;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.object.Anime;

public class GoGoAnimeList extends BaseScraper{
	public ArrayList<Anime> scrape(String website) {
		ArrayList<Anime> links = new ArrayList<Anime>();
		GoGoAnime scraper = new GoGoAnime();
		
		try {
			Document html = Jsoup.connect(website).get();
			
			Element animeList = html.selectFirst("div.anime_list_body");
			Element list = animeList.selectFirst("ul.listing");
			Elements animes = list.select("li");
			
			for(Element anime : animes) {
				links.add(scraper.scrape("https://gogoanime3.net" + anime.selectFirst("a").attr("href")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return links;
	}
}
