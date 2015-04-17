package com.hackbulgaria.corejava;

public class FaultyProblem3 {
    
    public String reverseEveryWordInString(String sentence){
    	int len = sentence.length();
        StringBuilder arrayOfStrings = new StringBuilder();
        char[] inputString = sentence.toCharArray();
        StringBuilder subStr = new StringBuilder();
        for (int i = 0; i < len; i++){
            if (inputString[i] == ' '){
                arrayOfStrings.append(subStr.reverse());
                arrayOfStrings.append(" ");
                subStr.delete(0, subStr.length());
            }
            else{
                subStr.append(inputString[i]);
            }
        }
        arrayOfStrings.append(subStr.reverse());
        return arrayOfStrings.toString();
    }
}
