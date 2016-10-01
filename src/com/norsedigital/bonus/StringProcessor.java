package com.norsedigital.bonus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringProcessor {

	public StringProcessor(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String inputString;
		try {
			inputString = reader.readLine();
				
		int max = 0;
		int i = 1;
		String output = "";
		String processingString = inputString.substring(0, 1);
		
		while(i < inputString.length()){
			String temp = inputString.substring(i, i+1);
			if (!processingString.contains(temp)){
				processingString = processingString + temp;
				if (processingString.length() > max){
					output = processingString;
				}				
				i++;
			} else {
				if (processingString.length() > max){
					output = processingString;
					max = processingString.length();					
				} 
					processingString = processingString.substring(processingString.indexOf(temp) + 1);
					i = i + processingString.indexOf(temp) + 1;					
				}				
			}
		System.out.println(output);
		} catch (IOException e) {		
			e.printStackTrace();
		}
			
	}
	
	public static void main(String[] args) {
		new	StringProcessor();
	}
}			


