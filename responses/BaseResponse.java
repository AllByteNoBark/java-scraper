package main.responses;

import java.util.ArrayList;

public class BaseResponse {
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
		_TextResponse writer = new _TextResponse(file);
		for(Object obj : this.objects) {
			System.out.println(obj.toString());
			writer.write(obj.toString());
		}
	}
}
