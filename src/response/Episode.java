package response;

public class Episode {
	private int number;
	private String link;
	
	public Episode(int number, String link) {
		this.number = number;
		this.link = link;
	}

	public int getNumber() {
		return number;
	}

	public String getLink() {
		return link;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Episode [number=" + number + ", link=" + link + "]";
	}
}
