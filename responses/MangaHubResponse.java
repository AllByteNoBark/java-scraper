package main.responses;

import static main.util.Utility.print;

public class MangaHubResponse extends BaseResponse {
	public void toText() {
		print("Exporting MangaHub data to txt!");
		this.toText("IO/MangaHub/output.txt");
	}
}
