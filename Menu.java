package main;

import static main.util.Utility.*;

import main.thread.GoGoAnimeThread;
import main.thread.MangaHubThread;

public class Menu {
	public static void run() {
		while(true) {
			String input = (String) input("", String.class);
			String[] args = input.split(" ");

			if(args[0].equalsIgnoreCase("exit") || args[0].equalsIgnoreCase("!")) {
				break;
			}
			
			if(args[0].equalsIgnoreCase("help") || args[0].equals("h")) {
				print("> scrape [site] [export type] [optional:threads]"
						+ "\n> Supported sites:"
						+ "\n		- gogoanime3.net"
						+ "\n		- mangahub.io"
						+ "\n> exit");
				continue;
			}
			
			if(args[0].equalsIgnoreCase("scrape")) {
				if(args.length < 3) { print("Not enough arguments passed."); continue; }
				
				int threads = 1;
				if(args.length == 4) {
					if(Integer.parseInt(args[3]) > 0) {
						threads = Integer.parseInt(args[3]);
					}
				}
				
				String saveType = args[2];
				
				switch(args[1]) {
					case "gogoanime3.net":
						GoGoAnimeThread scraperGoGoAnime = new GoGoAnimeThread();
						scraperGoGoAnime.scrape(threads, saveType.toLowerCase());
						break;
					case "mangahub.io":
						MangaHubThread scraperMangaHub = new MangaHubThread();
						scraperMangaHub.scrape(threads, saveType.toLowerCase());
						break;
					default:
						print("Site requested not supported.");
				}
				
				continue;
			}
			
			
		}
	}
}
