package dao.impl;

import dao.ItemDAO;
import entity.Food;
import entity.Item;
import entity.Type;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addFood() {
//        @Enumerated(EnumType.STRING)
//        private Type type;
//        @Column(name = "preparation_time")
//        private int preparationTime;
//        @Column(name = "serving_time")
//        private int servingTime;
        Food food = new Food("F001", "name", 1.0, "description", true, Type.APPETIZER, 1, 1);
        ItemImpl itemImpl = new ItemImpl();
        assertTrue(itemImpl.addFood(food));
    }

    @Test
    void listSpecialItemsBySupplier() {
        ItemImpl itemImpl = new ItemImpl();
        List<Item> items = itemImpl.listSpecialItemsBySupplier("Bob");
        assertEquals(1, items.size());
    }
}