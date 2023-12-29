package scraper;

import static util.Utility.print;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import response.Episode;

public class GoGoEpisode {
	private final String domain = "https://gogoanime3.net";
	public Episode scrape(String link, int number) {
		String vidLink = "";
		try {
			Document html = Jsoup.connect(this.domain + link).get();
			
			vidLink = html.selectFirst("div.anime_video_body_watch > div#load_anime > div.anime_video_body_watch_items > div.play-video > iframe").attr("src");
		} catch (IOException e) {
			print("[Error]: " + e.getMessage() + " [web]: " + link);
		}
		
		return new Episode(number, vidLink);
	}
}
