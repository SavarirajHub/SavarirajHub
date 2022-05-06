package com.rfpio.restaurant.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FileDataReader {
	public static  List<String> getFilteredListFromFile(String fileName , Set<String> items ){
		BufferedReader br = null;
		 try {
			 List<String> filteredList =   new ArrayList<String>();
			 br = new BufferedReader(new FileReader(fileName));
			 String row;
				while ((row = br.readLine()) != null)   {
				  if(isRowContainsItems(row, items)){
					  filteredList.add(row);
				  }
				}
				return filteredList;
			} catch (Exception exception) {
				throw new RuntimeException("Exception in getFilteredListFromFile", exception);
			} finally {
				if(null != br) {
					try {
						br.close();
					} catch (Exception exception) { }
				}
			}
			
		}	
		
		public static boolean isRowContainsItems(String row , Set<String> items){			
			for(String item : items){
				if(row.contains(item.trim()))
					return true;
			}
			return false;
		}		
		
}
