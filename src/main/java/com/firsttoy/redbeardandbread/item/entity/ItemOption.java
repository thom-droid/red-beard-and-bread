package com.firsttoy.redbeardandbread.item.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemOptionId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Setter
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;
}
