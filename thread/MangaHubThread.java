package main.thread;

import main.factory.MangaHubListFactory;

public class MangaHubThread {
	public void scrape(int threads) {
		MangaHubListFactory[] lists = new MangaHubListFactory[threads];
		
		for(int i = 1; i <= threads; i++) {
			lists[i-1] = new MangaHubListFactory(i, threads);
		}
		
		for(MangaHubListFactory list : lists) {
			list.start();
		}
	}
}
