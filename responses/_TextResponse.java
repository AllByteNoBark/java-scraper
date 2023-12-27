package main.responses;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class _TextResponse {
	private FileWriter file = null;
	private BufferedWriter writer = null;
	
	public _TextResponse(String filepath) {
		try {
			this.file = new FileWriter(filepath, true);
			this.writer = new BufferedWriter(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String data) {
		try {
			this.writer.write(data + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			this.writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
