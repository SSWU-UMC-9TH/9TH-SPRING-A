package spring.umc.domain.store.entity;


import spring.umc.domain.member.enums.Address;
import spring.umc.global.BaseEntity;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column( length = 50)
    private String lane;

    @Column(length = 20)
    private String phoneNum;

    @Column(length = 50)
    private String detailAddress;


    private LocalTime openingHours;

    private LocalTime closingHours;


    @Enumerated(EnumType.STRING) //  Region-> Address Enum 변경
    @Column(nullable = false, length = 15)
    private Address address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();


    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();
}