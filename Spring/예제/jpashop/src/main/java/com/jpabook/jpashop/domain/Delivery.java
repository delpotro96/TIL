package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

  @Id
  @GeneratedValue
  @Column(name = "delivery_id")
  private Long id;

  @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
  private Order order;

  @Embedded private Address address;

  @Enumerated(EnumType.STRING) // Ordinary 절대 쓰면 안됨 중간에 삽입되면 한글자씩 밀려서 에러남
  private DeliveryStatus status;
}
