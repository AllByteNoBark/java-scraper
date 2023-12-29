package saver;

import java.util.ArrayList;

public class BaseSaver {
	private ArrayList<Object> objects = new ArrayList<>();
	
	public ArrayList<Object> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<Object> objects) {
		this.objects = objects;
	}
	
	public void add(Object obj) {
		this.getObjects().add(obj);
	}

	public void toText(String file) {
		_TextSaver writer = new _TextSaver(file);
		for(Object obj : this.objects) {
			writer.write(obj.toString());
		}
		writer.close();
	}
	
	public void toText(int pageNum) {}
	
	public void toJSON(String file) {
		_TextSaver writer = new _TextSaver(file);
		_JSONSaver json = new _JSONSaver();
		for(Object obj : this.objects) {
			writer.write(json.convert(obj) + ",\n");
		}
		writer.close();
	}
	
	public void toJSON(int pageNum) {}
}
