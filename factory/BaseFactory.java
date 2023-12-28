package main.factory;

import static main.util.Utility.print;

import main.saver.BaseSaver;
import main.scraper.BaseListScraper;

public class BaseFactory {
	public void scrape(int startingPoint, int increment, String type, String listLink, BaseListScraper site) {
		int i = startingPoint;
		String link = listLink + i;
		while(checkNextPage(link)) {
			saveResponse(site.scrape(link), type, i);
			
			i += increment;
			link = listLink + i;
		}
	}
	
	private void saveResponse(BaseSaver instance, String type, int pageNum) {
		switch(type.toLowerCase()) {
			case "text":
				instance.toText(pageNum);
				break;
			case "json":
				instance.toJSON(pageNum);
				break;
			case "mysql":
				instance.toText(pageNum);
				break;
			case "xml":
				instance.toText(pageNum);
				break;
			default:
				print("Data type not supported!");
				break;
		}
	}
	
	public boolean checkNextPage(String website) { return false; }
}
