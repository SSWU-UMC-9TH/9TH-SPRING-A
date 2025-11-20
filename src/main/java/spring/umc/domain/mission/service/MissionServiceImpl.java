package spring.umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.mission.entity.MissionImage;
import spring.umc.domain.mission.repository.MissionImageRepository;
import spring.umc.domain.mission.repository.MissionRepository;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.exception.StoreException;
import spring.umc.domain.store.exception.code.StoreErrorCode;
import spring.umc.domain.store.repository.StoreRepository;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MissionImageRepository missionImageRepository;

    @Override
    @Transactional
    public Mission createMission(Long storeId, String content, Integer point, LocalDate deadline, String imageUrl) { // 4. 시그니처 수정
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        MissionImage missionImage = MissionImage.builder()
                .missionImageUrl(imageUrl)
                .sequence(1)
                .build();

        MissionImage savedMissionImage = missionImageRepository.save(missionImage);

        Mission mission = Mission.builder()
                .store(store)
                .content(content)
                .point(point)
                .deadline(deadline)
                .missionImage(savedMissionImage)
                .build();

        return missionRepository.save(mission);
    }

    @Override
    public Page<Mission> searchMissions(Long storeId, Integer minPoint, LocalDate deadlineBefore, Pageable pageable) {
        return missionRepository.searchMissions(storeId, minPoint, deadlineBefore, pageable);
    }
}