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
     * Full Address
     */
    @OneToOne(mappedBy = "estate", cascade = CascadeType.ALL)
    private FullAddress fullAddress;
    /**
     * Metric Area
     */
    @Column(name = "metric_area")
    private Float metricArea;
    /**
     * Number of Rooms
     */
    @Column(name = "number_of_rooms")
    private Integer numberOfRooms;

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
