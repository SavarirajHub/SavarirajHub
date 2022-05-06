package com.rfpio.restaurant.controller;

import java.util.Set;

import com.rfpio.restaurant.service.RestaurantService;

public class RestaurantController {
	
	public static String getBestPriceRestaurant(String fileName, Set<String> items) {
		RestaurantService restaurant = RestaurantService.getInstance();
		return restaurant.getBestPrice(fileName, items);
	}

}
