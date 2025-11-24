package spring.umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.mission.entity.Mission;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findAllByStoreId(Long storeId); //아직구현 X
}