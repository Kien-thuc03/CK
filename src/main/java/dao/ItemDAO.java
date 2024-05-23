package dao;

import entity.Food;
import entity.Item;

import java.util.List;

public interface ItemDAO {
    public boolean addFood(Food food);
    public List<Item> listSpecialItemsBySupplier(String supplierName);
}
