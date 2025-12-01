package spring.umc.domain.mission.entity.mapping;
import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.mission.entity.Mission;

@Entity
@Table(name = "user_mission",
        uniqueConstraints = @UniqueConstraint(name = "uk_user_mission_member_mission",
                columnNames = {"user_id","mission_id"}))
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long id;

    @Column(name = "is_complete", nullable = false)
    @Builder.Default
    private boolean isComplete = false;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private Member member;
}
