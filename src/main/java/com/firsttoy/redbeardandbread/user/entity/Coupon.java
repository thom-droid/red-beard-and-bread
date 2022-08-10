package com.firsttoy.redbeardandbread.user.entity;

import com.firsttoy.redbeardandbread.audit.Auditable;
import com.firsttoy.redbeardandbread.item.entity.Item;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int discount;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime issuedAt;
    private LocalDateTime appliedAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private User user;

    @Builder.Default
    @OneToMany
    private List<Item> items = new ArrayList<>();

    public void setAvailable() {
        isAvailable = issuedAt.isBefore(expiredAt) || appliedAt == null;
    }

    //Todo discount: strategy pattern ; welcome, revisit, mission ...
}
