package dao.impl;

import dao.FoodDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FoodImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listFoodAndCost() {
        FoodDAO foodDAO = new FoodImpl();
        Map foodAndCost = foodDAO.listFoodAndCost();
        assertEquals(7, foodAndCost.size());
    }
}