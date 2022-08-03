package com.firsttoy.redbeardandbread.user.entity;

import com.firsttoy.redbeardandbread.audit.Auditable;
import com.firsttoy.redbeardandbread.item.entity.Item;
import com.firsttoy.redbeardandbread.orders.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity(name = "Users")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private String email;

    @Builder.Default
    private UserRole role = UserRole.USER;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Coupon> coupons = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "USERS_LIKED_ITEMS",
            joinColumns = @JoinColumn(name = "USERS_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private Set<Item> items = new HashSet<>();

    @Getter
    public enum UserRole {
        USER,
        ADMIN,
    }
}
