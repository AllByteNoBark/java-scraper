package main.responses;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter;

public class _JSONResponse {
	private ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
	
	public String convert(Object obj) {
		try {
			return this.mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
