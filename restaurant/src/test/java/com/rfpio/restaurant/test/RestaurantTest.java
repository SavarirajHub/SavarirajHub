package com.rfpio.restaurant.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.rfpio.restaurant.controller.RestaurantController;

public class RestaurantTest {

	public static void main(String[] args) {
		List<String> items = new ArrayList<>(Arrays.asList(args));
		String fileName = items.remove(0);
		System.out.println(RestaurantController.getBestPriceRestaurant(fileName, new HashSet<>(items)));
	}

}
