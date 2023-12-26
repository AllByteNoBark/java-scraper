package main.object;

public class Manga {
	private String name;
	private String summary;
	private String author;
	private String artist;
	private String status;
	
	public Manga(String n, String s, String au, String ar, String status) {
		this.name = n;
		this.summary = s;
		this.author = au;
		this.artist = ar;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public String getSummary() {
		return summary;
	}

	public String getAuthor() {
		return author;
	}

	public String getArtist() {
		return artist;
	}

	public String getStatus() {
		return status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Manga [name=" + name + ", summary=" + summary + ", author=" + author + ", artist=" + artist
				+ ", status=" + status + "]";
	}
}
