package ru.chameleon.estate.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "estate")
public class EstateEntity {
    /**
     * Estate Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estate_id")
    private Long estateId;
    /**
     * necessary
     * Full Address
     */
    @OneToOne(mappedBy = "estate", cascade = CascadeType.ALL)
    private FullAddress fullAddress;
    /**
     * necessary
     * Number of Rooms
     */
    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;
    /**
     * necessary
     * Number of Rooms
     */
    @Column(name = "number_of_floors")
    private Integer numberOfFloors;
    /**
     * necessary
     * New building or Old building
     */
    @Column(name = "segment")
    private String segment;
    /**
     * necessary
     * Bricks, panels or monolith
     */
    @Column(name = "material_of_building")
    private String materialOfBuilding;

    /**
     * could be corrected
     * Number of current apartment floor
     */
    @Column(name = "current_floor")
    private Integer currentFloor;
    /**
     * could be corrected
     * Metric Area
     */
    @Column(name = "metric_area")
    private Float metricArea;
    /**
     * could be corrected
     * Metric of Kitchen
     */
    @Column(name = "metric_kitchen")
    private Float metricKitchen;
    /**
     * could be corrected
     * The presence of a balcony
     */
    @Column(name = "has_balcony")
    private boolean hasBalcony;
    /**
     * could be corrected
     * Distance to the nearest metro
     */
    @Column(name = "distance_to_metro")
    private Float distanceToMetro;
    /**
     * could be corrected
     * State of apartment:
     * without renovation, renovated apartment, municipal renovation
     */
    @Column(name = "state")
    private String state;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EstateEntity that = (EstateEntity) o;
        return estateId != null && Objects.equals(estateId, that.estateId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
