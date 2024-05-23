package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description
 * @author: nktng,
 * @date:23/05/2024,
 */
@Entity
@Getter
@Setter
@Table(name = "beverages")
public class Beverage extends Item implements Serializable {
    @Enumerated(EnumType.STRING)
    private Size size;
    @Column(name = "supplier_name")
    private String supplierName;

    public Beverage() {
    }

    public Beverage(Size size, String supplierName) {
        this.size = size;
        this.supplierName = supplierName;
    }

    public Beverage(String id, String name, double price, String description, boolean onSpecial, Size size, String supplierName) {
        super(id, name, price, description, onSpecial);
        this.size = size;
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "size=" + size +
                ", supplierName='" + supplierName + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", onSpecial=" + onSpecial +
                '}';
    }
}
