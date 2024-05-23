package dao.impl;

import dao.ItemDAO;
import entity.Food;
import entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

/**
 * @description
 * @author: nktng,
 * @date:23/05/2024,
 */
public class ItemImpl implements ItemDAO {
    EntityManager em;
    EntityTransaction et;

    public ItemImpl() {
        em = Persistence.createEntityManagerFactory("SQLBD").createEntityManager();
        et = em.getTransaction();
    }

//    Thêm một món ăn mới. Trong đó, mã số của món phải bắt đầu là F và theo sau ít
//    nhất 3 ký số.
//            + addFood (food: Food) : boolean

    @Override
    public boolean addFood(Food food) {
        try {
            if (!food.getId().matches("^F\\d{3,}$")) {
                return false;
            }
            et.begin();
            em.persist(food);
            et.commit();
            return true;
        } catch (Exception e) {
            et.rollback();
            return false;
        }
    }

//    Liệt kê danh sách mặt hàng là món đặt biệt của nhà hàng mà có sử dụng nguyên
//    liệu được nhập từ nhà cung cấp nào đó khi biết tên nhà cung cấp (tìm tương đối, không phân biệt
//            chữ thường hoa).
    @Override
    public List<Item> listSpecialItemsBySupplier(String supplierName) {
        return em.createNamedQuery("Item.ListSpecialItemsBySupplier", Item.class)
                .setParameter("supplierName", supplierName)
                .getResultList();
    }
    public void close() {
        em.close();
        em = null;
    }
}
