package com.firsttoy.redbeardandbread.item.entity;

import com.firsttoy.redbeardandbread.audit.Auditable;
import com.firsttoy.redbeardandbread.special_offer.entity.SpecialOffer;
import com.firsttoy.redbeardandbread.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Item extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    //Todo : unique
    @Column(updatable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private String descriptionImage;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false)
    private Category category;

    private int likeHitCount;
    private int saleScore;
    private boolean isSpeciallyOffered;
    private boolean isCouponApplicable;

    @Builder.Default
    private SaleStatus salesStatus = SaleStatus.ON_SALE;

    @ManyToOne
    @JoinColumn(name = "SPECIALOFFER_ID")
    private SpecialOffer specialOffer;

    @Builder.Default
    @ManyToMany(mappedBy = "items")
    private Set<User> users = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "item")
    private List<ItemOption> itemOptions = new LinkedList<>();

    @Getter
    public enum Category {
        BREAD(1, "식빵 및 단팥빵"),
        CAKE(2, "케이크 및 디저트"),
        SALAD(3, "사라다"),
        GIFT(4, "선물용"),
        ;

        private final int code;
        private final String desc;

        Category(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
    @Getter
    public enum SaleStatus {

        ON_SALE(1, "정상판매중"),
        SHORTAGE(2, "품절임박"),
        OUT_OF_STOCK(3, "재고소진"),
        UNAVAILABLE(4, "판매종료");

        private final int code;
        private final String desc;

        SaleStatus(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

}
