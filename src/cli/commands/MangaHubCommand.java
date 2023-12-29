package cli.commands;

import static util.Utility.print;

import java.util.List;

import factory.MangaHubListFactory;

public class MangaHubCommand extends BaseCommand {
	private int requiredArguments = 1;
	
	public void scrape(List<String> args) {
		int threads = setThreads(args);
		
		Thread[] lists = new Thread[threads];
		
		if(args.size() < this.requiredArguments) {
			print("The arguments do not match what is required.");
			return;
		}
		
		for(int i = 1; i <= threads; i++) {
			int j = i;
			
			args.get(this.requiredArguments-1);
			
			lists[i-1] = new Thread(){
				public void run() {
					MangaHubListFactory scraper = new MangaHubListFactory();
					scraper.scrape(j, threads, args.get(0));
				}
			};
			
			
		}
		
		for(Thread list : lists) {
			list.start();
		}
		
		for(Thread list : lists) {
			try {
				list.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		print("MangaHub finished! All the data was saved as " + args.get(0) + "!");
	}
	
	private int setThreads(List<String> args) {
		if(args.size() > 1) {
			if(Integer.parseInt(args.get(1)) > 0) {
				return Integer.parseInt(args.get(1));
			}
		}
		return 1;
	}
}
