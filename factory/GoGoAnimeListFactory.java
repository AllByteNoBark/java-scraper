package main.factory;

import static main.util.Utility.print;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import main.object.Anime;
import main.scraper.GoGoAnimeList;

public class GoGoAnimeListFactory {
	public void createList(String website) {
		GoGoAnimeList site = new GoGoAnimeList();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("IO/GoGoAnime/output.txt"))) {
			
			for(int i = 1; i <= 95; i++) {
				for(Anime anime : site.scrape(website + "anime-list.html?page=" + i)) {
					writer.write(anime.toString() + "\n");
				}
				print("Page: " + i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
