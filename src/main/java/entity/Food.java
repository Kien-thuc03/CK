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
@Table(name = "foods")
public class Food extends Item implements Serializable {
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "preparation_time")
    private int preparationTime;
    @Column(name = "serving_time")
    private int servingTime;

    public Food() {
    }

    public Food(String id, String name, double price, String description, boolean onSpecial, Type type, int preparationTime, int servingTime) {
        super(id, name, price, description, onSpecial);
        this.type = type;
        this.preparationTime = preparationTime;
        this.servingTime = servingTime;
    }

    public Food(Type type, int preparationTime, int servingTime) {
        this.type = type;
        this.preparationTime = preparationTime;
        this.servingTime = servingTime;
    }

    @Override
    public String toString() {
        return "Food{" +
                "type=" + type +
                ", preparationTime=" + preparationTime +
                ", servingTime=" + servingTime +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", onSpecial=" + onSpecial +
                '}';
    }
}
