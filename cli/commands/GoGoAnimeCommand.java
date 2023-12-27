package main.cli.commands;

import static main.util.Utility.print;

import java.util.List;

import main.factory.GoGoAnimeListFactory;

public class GoGoAnimeCommand extends BaseCommand {
	private int requiredArguments = 1;
	
	public void scrape(List<String> args) {
		int threads = setThreads(args);
		
		Thread[] lists = new Thread[threads];
		
		try {
			for(int i = 1; i <= threads; i++) {
				int j = i;
				
				args.get(this.requiredArguments-1);
				
				lists[i-1] = new Thread(){
					public void run() {
						GoGoAnimeListFactory scraper = new GoGoAnimeListFactory();
						scraper.scrape(j, threads, args.get(0));
					}
				};
				
				
			}
			
			for(Thread list : lists) {
				list.start();
			}
		} catch (IndexOutOfBoundsException e) {
			print("The arguments do not match what is required.");
		}
	}
	
	private int setThreads(List<String> args) {
		if(args.size() == 2) {
			if(Integer.parseInt(args.get(1)) > 0) {
				return Integer.parseInt(args.get(1));
			}
		}
		return 1;
	}
}
