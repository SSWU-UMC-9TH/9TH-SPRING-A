package spring.umc.domain.member.entity;

import spring.umc.global.BaseEntity;
import spring.umc.domain.member.enums.MissionStatus;
import spring.umc.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'IN_PROGRESS'")
    private MissionStatus status;

    private Boolean isFinished;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void updateStatusToComplete() {
        this.status = MissionStatus.COMPLETED;
        this.isFinished = true; // 관련 필드들을 함께 변경하여 데이터 일관성 유지
    }
}