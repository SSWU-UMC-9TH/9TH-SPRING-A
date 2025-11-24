package spring.umc.domain.member.entity;

import spring.umc.global.BaseEntity;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.member.enums.Status;
import spring.umc.domain.member.enums.Address;
import spring.umc.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="name", nullable = false, length = 5)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="gender", nullable=false)
    @Builder.Default
    private Gender gender=Gender.NONE;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(length = 50)
    private String detailAddress;

    @Builder.Default //4주차 피드백 수정-1
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private Status status=Status.ACTIVE; //4주차 피드백 수정-1

    private LocalDateTime inactiveDate;

    @Column(length = 50)
    private String email;

    private Integer point;

    @Column(length = 20)
    private String phoneNum;


    @OneToOne(mappedBy = "member", cascade = CascadeType.REMOVE)
    private Favor favor;


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<UserMission> userMissionList = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Qna> qnaList = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<UserTerms> userTermsList = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Alarm> alarmList = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<PointHistory> pointHistoryList = new ArrayList<>();
}