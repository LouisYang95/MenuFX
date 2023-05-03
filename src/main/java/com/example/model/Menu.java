package com.example.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Class that represents the menu of the restaurant
 */
public class Menu {
    private List<Dish> dishes;

    public Menu(List<Dish> dishes) {
        this.dishes = dishes;
    }
    /**
     * Constructor to initialize the list of dishes
     */
    public Menu() {
        dishes = new ArrayList<>();
    }
    /**
     * Get the list of dishes
     * @return The list of dishes
     */
    public List<Dish> getDishes() {
        return dishes;
    }

    /**
     * Add a dish to the menu
     * @param dish The dish to add
     */
    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    /**
     * Remove a dish from the menu
     * @param dish The dish to remove
     */
    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }
}
