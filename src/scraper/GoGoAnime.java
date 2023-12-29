package scraper;

import static util.Utility.print;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import response.Anime;
import response.Episode;

public class GoGoAnime {
	private final String domain = "https://gogoanime3.net";
	public Anime scrape(String website) {
		GoGoEpisode scraper = new GoGoEpisode();
		
		String name = "";
		String description = "";
		int year = 0;
		int episodes = 0;
		String art = "";
		Episode[] episodeLinks = null;
		
		try {
			Document html = Jsoup.connect(this.domain + website).get();
			
			Element animeInfoBody = html.selectFirst("div.anime_info_body").selectFirst("div.anime_info_body_bg");
			
			if(animeInfoBody == null) return new Anime(name, description, year, episodes, art, episodeLinks);
			
			name = animeInfoBody.selectFirst("h1").text();
			art = animeInfoBody.selectFirst("img").attr("src");
			
			episodes = Integer.parseInt(html.selectFirst("div.anime_video_body").selectFirst("ul").selectFirst("li").selectFirst("a").attr("ep_end"));
			
			episodeLinks = new Episode[episodes];
			
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
			
			for(int i = 1; i <= episodes; i++) {
				try {
					episodeLinks[i-1] = scraper.scrape(website.substring(9, website.length()) + "-episode-" + i, i);
				} catch (IndexOutOfBoundsException e) {
					print(e.getMessage() + " | " + website);
				}
			}
		} catch (IOException e) {
			print("[Error]: " + e.getMessage() + " [web]: " + website);
			print("Retrying.");
			scrape(website);
		}
		
		return new Anime(name, description, year, episodes, art, episodeLinks);
	}
}
