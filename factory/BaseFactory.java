package main.factory;

import static main.util.Utility.print;

import main.responses.BaseResponse;
import main.scraper.BaseScraper;

public class BaseFactory {
	public void scrape(int startingPoint, int increment, String type, String listLink, BaseScraper site) {
		int i = startingPoint;
		String link = listLink + i;
		do {
			saveResponse(site.scrape(link), type);
			
			i += increment;
			link = listLink + i;
		} while(checkNextPage(listLink));
	}
	
	private void saveResponse(BaseResponse instance, String type) {
		switch(type.toLowerCase()) {
			case "text":
				instance.toText();
				break;
			case "json":
				instance.toJSON();
				break;
			case "mysql":
				instance.toText();
				break;
			case "xml":
				instance.toText();
				break;
			default:
				print("Data type not supported!");
				break;
		}
	}
	
	public boolean checkNextPage(String website) { return false; }
}
