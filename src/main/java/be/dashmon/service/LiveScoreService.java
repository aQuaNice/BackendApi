package be.dashmon.service;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.dashmon.domain.Batsman;


@Component
public class LiveScoreService {
    
    private List<Batsman> scoresList = initialScores();
    
    
    
    public List<Batsman> getTodaysRegis(){
		Random rand = new Random();
		
		int randomNum = rand.nextInt((2 - 1) + 1);
		Batsman batsman = scoresList.get(randomNum);

		batsman.setBalls(batsman.getBalls() + 1);
		batsman.setRuns(batsman.getRuns() + 1);
	return scoresList;
    }
    
    
    
    public List<Batsman> getScore(){
			Random rand = new Random();
			
			int randomNum = rand.nextInt((2 - 1) + 1);
			Batsman batsman = scoresList.get(randomNum);

			batsman.setBalls(batsman.getBalls() + 1);
			batsman.setRuns(batsman.getRuns() + 1);
		return scoresList;
    }

    private List<Batsman> initialScores() {
        Batsman sachin = new Batsman();
        sachin.setName("Sachin Tendulkar");
        sachin.setRuns(24);
        sachin.setBalls(26);
        Batsman ganguly = new Batsman();
        ganguly.setName("Sourav Ganguly");
        ganguly.setRuns(28);
        ganguly.setBalls(30);
        List<Batsman> scoresList = new ArrayList<Batsman>();
        scoresList.add(sachin);
        scoresList.add(ganguly);
//    	try {
//			
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return mapper.writeValueAsString(respon);

    	
        return scoresList;
    }

}