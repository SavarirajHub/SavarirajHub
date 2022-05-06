package com.rfpio.restaurant.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.rfpio.restaurant.model.Restaurant;
import com.rfpio.restaurant.util.FileDataReader;

public class RestaurantService {
	private static RestaurantService restaurantService;
	
	public static RestaurantService getInstance() {
		if(null == restaurantService) {
			synchronized(RestaurantService.class) {
				if(null == restaurantService) {
					restaurantService = new RestaurantService();
				}
			}
		}
		return restaurantService;
	}
	
	private RestaurantService() { }
	
	public String getBestPrice(String fileName, Set<String> Items) {
		List<String> itemMatchedList = FileDataReader.getFilteredListFromFile(fileName, Items);
		
		Map<Integer, Set<Restaurant>> restaurantMap =  getRestaurantGroupedMap(itemMatchedList);
		
		int restaurantId = -1;
		double minPrice = Float.MAX_VALUE;
		double tmpPrice = 0;
		boolean isAllItemAvailable = false;
		
		for(Map.Entry<Integer, Set<Restaurant>> map : restaurantMap.entrySet()) {
			List<String> itemList = new LinkedList<>(Items);
			Set<Restaurant> restaurantSet = map.getValue();
			tmpPrice = 0;
			for(Restaurant restaurant : restaurantSet) {
				Set<String> samePriceItems = restaurant.getItems();
				for(int i = 0; i < itemList.size(); i++) {
					System.out.println(samePriceItems);
					if(samePriceItems.contains(itemList.get(i))) {
						itemList.remove(i);
						tmpPrice += restaurant.getPrice();
					}
				}
			}
			
			if(itemList.size() == 0 && tmpPrice < minPrice) {
				minPrice = tmpPrice;
				restaurantId = map.getKey();
				isAllItemAvailable = true;
			}
		}
		
		
		return isAllItemAvailable ? restaurantId + "," + minPrice : "Nill";
	}
	
	public Map<Integer, Set<Restaurant>> getRestaurantGroupedMap(List<String> restaurantData) {
		
		Set<Restaurant> set = new HashSet<>();
		for(String row : restaurantData) {
			isValidData(row);
			set.add(getRestaurant(row));
		}
		
		Map<Integer, Set<Restaurant>> map = set.stream().collect(Collectors.groupingBy((restaurant) -> restaurant.getRestaurantId(), Collectors.toSet()));
		
		return map;
	}
	
	public Restaurant getRestaurant(String row) {
		List<String> items = new ArrayList<>();
		items.addAll(Arrays.asList(row.split(",")));
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(items.remove(0).trim());
		restaurant.setPrice(items.remove(0).trim());
		getItemSet(restaurant, items);
		return restaurant;
	}
	
	public Restaurant getItemSet(Restaurant restaurant, List<String> items) {
		Set<String> set = new HashSet<>();
		for(String item : items) {
			set.add(item.trim());
		}
		restaurant.setItems(set);
		return restaurant;
	}
	
	public void isValidData(String row) {
		if(row.split(",").length < 3)
			throw new RuntimeException("Invalid File");
	}
}
