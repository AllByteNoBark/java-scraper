package main.scraper;

import main.saver.BaseSaver;

public class BaseListScraper {
	public BaseSaver scrape(String website) { return new BaseSaver(); }
}
