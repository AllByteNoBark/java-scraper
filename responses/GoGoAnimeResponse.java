package main.responses;

import static main.util.Utility.print;

public class GoGoAnimeResponse extends BaseResponse {
	public void toText() {
		print("Exporting GoGoAnime data to txt!");
		this.toText("IO/GoGoAnime/output.txt");
	}
}
