package main.thread;

import static main.util.Utility.print;

import main.factory.GoGoAnimeListFactory;

public class GoGoAnimeThread extends Thread {
	private int startingPoint;
	private int increment;
	private String saveType; 
	
	public GoGoAnimeThread(int start, int inc, String type) {
		this.startingPoint = start;
		this.increment = inc;
		this.saveType = type;
	}
	
	public void run() {
		GoGoAnimeListFactory scraper = new GoGoAnimeListFactory();
		switch(this.saveType) {
			case "text":
				scraper.scrape(this.startingPoint, this.increment).toText();
				break;
			case "json":
				scraper.scrape(this.startingPoint, this.increment).toText();
				break;
			case "mysql":
				scraper.scrape(this.startingPoint, this.increment).toText();
				break;
			case "xml":
				scraper.scrape(this.startingPoint, this.increment).toText();
				break;
			default:
				print("Data type not supported!");
				break;
		}
	}
}
