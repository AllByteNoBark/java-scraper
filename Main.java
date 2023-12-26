package main;

import main.factory.GoGoAnimeListFactory;
import main.factory.MangaHubListFactory;
import main.thread.GoGoAnimeThread;
import main.thread.MangaHubThread;

import static main.util.Utility.print;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		MangaHubListFactory testManga = new MangaHubListFactory();
		GoGoAnimeListFactory testAnime = new GoGoAnimeListFactory();
		MangaHubThread mangahub = new MangaHubThread();
		GoGoAnimeThread gogoanime = new GoGoAnimeThread();
		
		testManga.scrape("https://mangahub.io/").toText();
		testAnime.scrape("https://gogoanime3.net/").toText();
	}

}
