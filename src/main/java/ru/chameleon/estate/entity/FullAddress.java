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
@Table(name = "full_address")
public class FullAddress {
    /**
     * Full Address ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
    /**
     * Full Address
     */
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private EstateEntity estate;
    /**
     * City
     */
    @Column(name = "city")
    private String city;
    /**
     * District
     */
    @Column(name = "district")
    private String district;
    /**
     * Street
     */
    @Column(name = "street")
    private String street;
    /**
     * House Number
     */
    @Column(name = "house_number")
    private String houseNumber;
    /**
     * Entrance
     */
    @Column(name = "entrance")
    private String entrance;
    /**
     * Floor
     */
    @Column(name = "floor")
    private String floor;
    /**
     * Apartment number
     */
    @Column(name = "apartment_number")
    private String apartmentNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FullAddress that = (FullAddress) o;
        return addressId != null && Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
