package com.example.recipemagic.model;

import java.util.List;

public class Category {
    private List<Recipe> recipes;
    private String category;

    public String getCategorty(){
        return category;
    }

    public List<Recipe> getRecipes(){
        return recipes;
    }
}
