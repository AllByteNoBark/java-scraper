package cli.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BaseCommand {
	public void scrape(List<String> args) {}
	
	public void execute(BaseCommand instance, String method, List<String> args) {
		 try {
			BaseCommand.class.getMethod(method, List.class).invoke(instance, args);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
