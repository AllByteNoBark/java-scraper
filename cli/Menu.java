package main.cli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static main.util.Utility.*;

import main.cli.commands.BaseCommand;
import main.cli.commands.GoGoAnimeCommand;

public class Menu {
	public static void run() {
		while(true) {
			String input = (String) input("", String.class);
			String[] args = input.split(" ");
			
			HashMap<String, BaseCommand> commands = new HashMap<String, BaseCommand>();
			commands.put("gogoanime3.net", new GoGoAnimeCommand());
			commands.put("mangahub.io", new GoGoAnimeCommand());

			if(args[0].equalsIgnoreCase("exit") || args[0].equalsIgnoreCase("!")) {
				break;
			}
			
			if(args[0].equalsIgnoreCase("help") || args[0].equals("h")) {
				print("> scrape [site] [export type] [optional:threads]"
						+ "\n> Supported sites:"
						+ "\n		- gogoanime3.net"
						+ "\n		- mangahub.io"
						+ "\n> exit");
				continue;
			}

			List<String> sublistArgs = Arrays.asList(args);
			sublistArgs = sublistArgs.subList(2, sublistArgs.size());
			
			try {
				BaseCommand command = commands.get(args[1]);
				command.execute(command, args[0], sublistArgs);
			} catch(NullPointerException e) {
				print("Site not supported.");
			}
		}
	}
}