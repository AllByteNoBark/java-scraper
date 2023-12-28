package main.scraper;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.response.Anime;

import static main.util.Utility.print;

public class GoGoAnime {
	public Anime scrape(String website) {
		String name = "";
		String description = "";
		int year = 0;
		int episodes = 0;
		String art = "";
		
		try {
			Document html = Jsoup.connect(website).get();
			
			Element animeInfoBody = html.selectFirst("div.anime_info_body").selectFirst("div.anime_info_body_bg");
			
			if(animeInfoBody == null) return new Anime(name, description, year, episodes, art);
			
			name = animeInfoBody.selectFirst("h1").text();
			art = animeInfoBody.selectFirst("img").attr("src");
			
			episodes = Integer.parseInt(html.selectFirst("div.anime_video_body").selectFirst("ul").selectFirst("li").selectFirst("a").attr("ep_end"));
			
			Elements paragraphs = animeInfoBody.select("p.type");
			for(Element p : paragraphs) {
				String temp = p.selectFirst("span").text();
				if(temp.equalsIgnoreCase("plot summary:")) {
					description = p.text();
					if(description.length() == temp.length()) {
						description = "Empty";
					} else {
						description = description.substring(temp.length()+1, description.length());
					}
				} else if(temp.equalsIgnoreCase("released:")) {
					if(p.text().length() == temp.length()) {
						year = 0;
					} else {
						year = Integer.parseInt(p.text().substring(temp.length()+1, p.text().length()));
					}
				}
			}
		} catch (IOException e) {
			print("[Error]: " + e.getMessage());
			if(e.getClass() == SocketTimeoutException.class) {
				print("Retrying.");
				scrape(website);
			}
		}
		
		return new Anime(name, description, year, episodes, art);
	}
}
