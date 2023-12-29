package saver;

import static util.Utility.print;

public class GoGoAnimeSaver extends BaseSaver {
	public void toText(int pageNum) {
		print("[Page: " + pageNum + "] Exporting GoGoAnime data to Txt!");
		this.toText("IO/GoGoAnime/response.txt");
	}
	
	public void toJSON(int pageNum) {
		print("[Page: " + pageNum + "] Exporting GoGoAnime data to json!");
		this.toJSON("IO/GoGoAnime/response.json");
	}
}
