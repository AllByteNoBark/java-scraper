package main.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.object.Anime;
import static main.util.Utility.print;

public class GoGoAnime extends BaseScraper {
	public Anime scrape(String website) {
		Anime anime = null;
		String name = null;
		String description = null;
		int year = 0;
		int episodes = 0;
		String art = null;
		
		try {
			print(website);
			Document html = Jsoup.connect(website).get();
			
			Element animeInfoBody = html.selectFirst("div.anime_info_body").selectFirst("div.anime_info_body_bg");
			
			name = animeInfoBody.selectFirst("h1").text();
			art = animeInfoBody.selectFirst("img").attr("src");
			
			episodes = Integer.parseInt(html.selectFirst("div.anime_video_body").selectFirst("ul").selectFirst("li").selectFirst("a").attr("ep_end"));
			
			Elements paragraphs = animeInfoBody.select("p.type");
			for(Element p : paragraphs) {
				String temp = p.selectFirst("span").text();
				if(temp.equalsIgnoreCase("plot summary:")) {
					description = p.text();
					description = description.substring(temp.length(), description.length());
				} else if(temp.equalsIgnoreCase("released:")) {
					year = Integer.parseInt(p.text().substring(temp.length()+1, p.text().length()));
				}
			}
			
			anime = new Anime(name, description, year, episodes, art);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return anime;
	}
}
