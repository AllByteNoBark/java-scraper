package main.thread;

import main.factory.MangaHubListFactory;

public class MangaHubThread {
	public void scrape(int threads, String saveType) {
		MangaHubListFactory[] lists = new MangaHubListFactory[threads];
		
		for(int i = 1; i <= threads; i++) {
			lists[i-1] = new MangaHubListFactory(i, threads, saveType);
		}
		
		for(MangaHubListFactory list : lists) {
			list.start();
		}
	}
}
