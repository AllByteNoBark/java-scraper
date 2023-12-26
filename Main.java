package main;

import main.factory.GoGoAnimeListFactory;
import main.factory.MangaHubListFactory;

import static main.util.Utility.print;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		MangaHubListFactory mangahub = new MangaHubListFactory();
		GoGoAnimeListFactory gogoanime = new GoGoAnimeListFactory();
		
		// mangahub.createList("https://mangahub.io/");
		gogoanime.createList("https://gogoanime3.net/");
	}

}
