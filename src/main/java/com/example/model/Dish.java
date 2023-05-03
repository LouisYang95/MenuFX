package com.example.model;

/**
 *  Dish class to create a new dish.
 */
public class Dish {
    private static int currentId = 0;
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;
    private String ingredients;

    /**
     * Constructor to initialize the dish
     * @param name          The name of the dish
     * @param description   The description of the dish
     * @param price         The price of the dish
     * @param image         The image of the dish
     * @param ingredients   The ingredients of the dish
     */
    public Dish(String name, String description, double price, String image, String ingredients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.ingredients = ingredients;
    }

    /**
     * Constructor to automatically increment and generate an id
     */
    public Dish() {
        this.id = currentId++;
    }

    public int getId() {
        return id;
    }
    /**
     * Change the dish's id.
     *
     * @param id The new id of the dish
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Get the name of the dish.
     *
     * @return The name of the dish
     */
    public String getName() {
        return name;
    }
    /**
     * Change the dish's name.
     *
     * @param name The new name of the dish
     */
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    /**
     * Change the dish's description.
     *
     * @param description The new description of the dish
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Get the price of the dish.
     *
     * @return The price of the dish
     */
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Get the image of the dish.
     *
     * @return The image of the dish
     */
    public String getImage() {
        return image;
    }
    /**
     * Change the dish's image.
     *
     * @param image The new image of the dish
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * Get the ingredients of the dish.
     *
     * @return The ingredients of the dish
     */
    public String getIngredients() {
        return ingredients;
    }
    /**
     * Change the dish's ingredients.
     *
     * @param ingredient The new ingredients of the dish
     */
    public void setIngredients(String ingredient) {
        ingredients = ingredient;
    }
}
