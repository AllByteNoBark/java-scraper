package main.saver;

import static main.util.Utility.print;

public class MangaHubSaver extends BaseSaver {
	public void toText(int pageNum) {
		print("[Page: " + pageNum + "] Exporting MangaHub data to txt!");
		this.toText("IO/MangaHub/response.txt");
	}
	
	public void toJSON(int pageNum) {
		print("[Page: " + pageNum + "] Exporting MangaHub data to JSON!");
		this.toJSON("IO/MangaHub/response.json");
	}
}
