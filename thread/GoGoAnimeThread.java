package main.thread;

import main.factory.GoGoAnimeListFactory;

public class GoGoAnimeThread {
	public void scrape(int threads, String saveType) {
		GoGoAnimeListFactory[] lists = new GoGoAnimeListFactory[threads];
		
		for(int i = 1; i <= threads; i++) {
			lists[i-1] = new GoGoAnimeListFactory(i, threads, saveType);
		}
		
		for(GoGoAnimeListFactory list : lists) {
			list.start();
		}
	}
}
