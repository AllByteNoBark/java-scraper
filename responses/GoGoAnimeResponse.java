package main.responses;

import static main.util.Utility.print;

public class GoGoAnimeResponse extends BaseResponse {
	public void toText() {
		print("Exporting GoGoAnime data to txt!");
		this.toText("IO/GoGoAnime/response.txt");
	}
	
	public void toJSON() {
		print("Exporting GoGoAnime data to json!");
		this.toJSON("IO/GoGoAnime/response.json");
	}
}
