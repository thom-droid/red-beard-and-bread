package com.firsttoy.redbeardandbread.orders.entity;

import com.firsttoy.redbeardandbread.audit.Auditable;
import com.firsttoy.redbeardandbread.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity(name = "Orders")
public class Order extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private int netPrice;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private int estimatedTimeOfCompletion;

    private int totalDiscount;
    private OrderStatus orderStatus;
    private OrderDenyingMessage orderDenyingMessage;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new LinkedList<>();

    @Getter
    public enum OrderStatus {

        ORDER_REQUESTED(1, "주문요청"),
        ORDER_ACCEPTED(2, "주문수락"),
        ORDER_COMPLETE(3, "주문조리완료"),
        ORDER_CANCELED(4, "주문취소"),
        ORDER_DENIED(5, "주문거절됨");

        private final int code;
        private final String description;

        OrderStatus(int code, String description) {
            this.code = code;
            this.description = description;
        }
    }

    @Getter
    public enum OrderDenyingMessage {
        OUT_OF_STOCK(1, "재고소진"),
        SNOWED_UNDER(2, "주문폭주"),
        CLOSED(3, "영업종료"),
        ETC(4, "가게사정");

        private final int code;
        private final String description;

        OrderDenyingMessage(int code, String description) {
            this.code = code;
            this.description = description;
        }
    }

}

