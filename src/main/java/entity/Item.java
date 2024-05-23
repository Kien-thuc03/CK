package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @description
 * @author: nktng,
 * @date:23/05/2024,
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "items")
@NamedQueries({
//        Thêm một món ăn mới. Trong đó, mã số của món phải bắt đầu là F và theo sau ít
//        nhất 3 ký số.
        @NamedQuery(name = "Item.AddFood", query = "INSERT INTO Food (id, name, price, description, onSpecial, type, preparationTime, servingTime) VALUES (:id, :name, :price, :description, :onSpecial, :type, :preparationTime, :servingTime)"),
//        Liệt kê danh sách mặt hàng là món đặt biệt của nhà hàng mà có sử dụng nguyên
//        liệu được nhập từ nhà cung cấp nào đó khi biết tên nhà cung cấp (tìm tương đối, không phân biệt
//        chữ thường hoa).
        @NamedQuery(name = "Item.ListSpecialItemsBySupplier", query = "SELECT i FROM Item i JOIN i.ingredients ig WHERE i.onSpecial = true AND lower(ig.supplierName) LIKE concat('%', lower(:supplierName), '%')"),
//        Tính giá gốc của từng món ăn sau khi chế biết thành phẩm. Kết quả sắp xếp giảm
//        dần theo đơn giá gốc.
//        Trong đó: Giá gốc món ăn = trung binh(số lượng nguyên liệu * đơn giá nguyên liệu) + (thời gian chuẩn
//bị + thời gian phục vụ) * 0.2$
//+ public listFoodAndCost(): Map<Food, Double>
        @NamedQuery(name = "Item.ListFoodAndCost", query = "SELECT f.id, f.name, f.price, f.description, f.onSpecial, f.preparationTime, f.servingTime, AVG(ig.price * ig.quantity) + (f.preparationTime + f.servingTime) * 0.2 FROM Food f JOIN f.ingredients ig GROUP BY f.id, f.name, f.price, f.description, f.onSpecial, f.preparationTime, f.servingTime ORDER BY f.price DESC")
})
public abstract class Item implements Serializable {
    @Id
    protected String id;
    protected String name;
    protected double price;
    protected String description;
    @Column(name = "on_special")
    protected boolean onSpecial;

// tao bang con item_ingredients voi 2 cot la item_id va ingredient_id de chi ra moi quan he nhieu nhieu giua item va ingredient
    @ManyToMany
    @JoinTable(
            name = "items_ingredients",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    protected Set<Ingredient> ingredients;




    public Item() {
    }

    public Item(String id, String name, double price, String description, boolean onSpecial) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.onSpecial = onSpecial;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", onSpecial=" + onSpecial +
                '}';
    }
}
