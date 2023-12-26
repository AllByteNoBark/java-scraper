package main.factory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import main.object.Manga;
import main.scraper.MangaHubList;

import static main.util.Utility.print;

public class MangaHubListFactory {
	public void createList(String website) {
		MangaHubList site = new MangaHubList();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("IO/MangaHub/output.txt"))) {
			for(int i = 1; i <= 1761; i++) {
				for(Manga manga : site.scrape(website + "search/page/" + i + "?q=&order=ALPHABET&genre=all")) {
					writer.write(manga.toString() + "\n");
				}
				print("Page: " + i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
