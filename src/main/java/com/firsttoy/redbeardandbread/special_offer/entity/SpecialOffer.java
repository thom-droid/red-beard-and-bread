package com.firsttoy.redbeardandbread.special_offer.entity;

import com.firsttoy.redbeardandbread.audit.Auditable;
import com.firsttoy.redbeardandbread.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class SpecialOffer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specialOfferId;
    private String name;
    private int discountRate;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @Builder.Default
    @OneToMany(mappedBy = "specialOffer")
    private List<Item> items = new ArrayList<>();

    //Todo startedAt: event listener - when special offer is over
    //Todo discountRate: strategy pattern for different discount
}
