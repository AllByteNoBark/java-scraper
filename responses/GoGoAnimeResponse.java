package main.responses;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

public class GoGoAnimeResponse {
	public void listResponse(ArrayList<String> list) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
            // Convert the object to JSON string
            for(String link : list) {
            	String jsonString = objectMapper.writeValueAsString(link);

                // Write the JSON string to a file
                File outputFile = new File("IO/GoGoAnime/animeList.json");
                objectMapper.writeValue(outputFile, link);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
