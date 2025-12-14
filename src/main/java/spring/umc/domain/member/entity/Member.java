package spring.umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import spring.umc.domain.member.entity.mapping.MemberFood;
import spring.umc.domain.member.entity.mapping.MemberTerm;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.store.enums.Address;
import spring.umc.global.auth.enums.SocialType;
import spring.umc.global.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 3, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", length = 255)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address", length = 255)
    private String detailAddress;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "phone_number", length = 255)
    private String phoneNumber;

    @Column(name = "point")
    private Integer point=0;

    @Column(name = "social_uid", length = 255)
    private String socialUid;

    @Column(name = "social_type", length = 255)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @OneToMany(mappedBy = "member")//, cascade = CascadeType.ALL, orphanRemoval = true
    @Builder.Default
    private List<MemberFood> memberFoods = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberTerm> memberTerms = new ArrayList<>();
}
