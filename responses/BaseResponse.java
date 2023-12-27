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
			writer.write(obj.toString());
		}
		writer.close();
	}
	
	public void toJSON(String file) {
		_TextResponse writer = new _TextResponse(file);
		_JSONResponse json = new _JSONResponse();
		for(Object obj : this.objects) {
			writer.write(json.convert(obj) + ",\n");
		}
		writer.close();
	}
}
