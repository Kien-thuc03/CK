package dao.impl;

import dao.FoodDAO;
import entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: nktng,
 * @date:23/05/2024,
 */
public class FoodImpl implements FoodDAO {
    EntityManager em;
    EntityTransaction et;

    public FoodImpl() {
        em = Persistence.createEntityManagerFactory("SQLBD").createEntityManager();
        et = em.getTransaction();
    }

    //        Tính giá gốc của từng món ăn sau khi chế biết thành phẩm. Kết quả sắp xếp giảm
//        dần theo đơn giá gốc.
//        Trong đó: Giá gốc món ăn = trung binh(số lượng nguyên liệu * đơn giá nguyên liệu) + (thời gian chuẩn
//bị + thời gian phục vụ) * 0.2$
//+ public listFoodAndCost(): Map<Food, Double>
    @Override
    public Map<Food, Double> listFoodAndCost() {
        List<Object[]> results = em.createNamedQuery("Item.ListFoodAndCost")
                .getResultList();
        Map<Food, Double> foodAndCost = new HashMap<>();
        for (Object[] result : results) {
            Food food = new Food();
            food.setId((String) result[0]);
            food.setName((String) result[1]);
            food.setPrice((Double) result[2]);
            food.setDescription((String) result[3]);
            food.setOnSpecial((Boolean) result[4]);
            food.setPreparationTime((Integer) result[5]);
            food.setServingTime((Integer) result[6]);
            Double cost = (Double) result[7];
            foodAndCost.put(food, cost);
        }
        return foodAndCost;
    }

    public void close() {
        em.close();
        em = null;
    }
}
