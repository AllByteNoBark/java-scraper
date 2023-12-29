package response;

import java.util.Arrays;

public class Anime {
	private String name;
	private String description;
	private int year;
	private int episodes;
	private String art;
	private Episode[] episodeLinks = null;
	
	public Anime(String n, String d, int y, int e, String art, Episode[] episodes) {
		this.name = n;
		this.description = d;
		this.year = y;
		this.episodes = e;
		this.art = art;
		this.episodeLinks = episodes;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getEpisodes() {
		return episodes;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public Episode[] getEpisodeLinks() {
		return episodeLinks;
	}

	public void setEpisodeLinks(Episode[] episodeLinks) {
		this.episodeLinks = episodeLinks;
	}

	@Override
	public String toString() {
		return "Anime [name=" + name + ", description=" + description + ", year=" + year + ", episodes=" + episodes
				+ ", art=" + art + ", episodeLinks=" + Arrays.toString(episodeLinks) + "]";
	}
}
