package com.example.autoserviceapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "order_id_seq",
            sequenceName = "order_id_seq",
            allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    private String problemDescription;
    private LocalDateTime orderDate;
    @ManyToMany
    @ToString.Exclude
    @JoinTable(name = "orders_servicings",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "servicing_id"))
    private List<Servicing> servicings;
    @ManyToMany
    @ToString.Exclude
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    private BigDecimal finalPrice;
    private LocalDateTime fulfillmentDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
