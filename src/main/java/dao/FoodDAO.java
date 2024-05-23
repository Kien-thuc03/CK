package dao;

import entity.Food;

import java.util.Map;

public interface FoodDAO {
    public Map<Food, Double> listFoodAndCost();
}
