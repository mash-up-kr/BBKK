package org.seoul.kk.cache;

import lombok.extern.slf4j.Slf4j;
import org.seoul.kk.entity.history.NicknameHistory;
import org.seoul.kk.repository.NicknameHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SyncNicknameHistoryScheduling {

    private static final long SYNC_DELAY_TIME = 1000L * 60;
    private static final long CLEAR_DELAY_TIME = 1000L * 60 * 30;

    @Autowired
    private NicknameHistoryRepository nicknameHistoryRepository;

    @Autowired
    private UsedNicknameCache usedNicknameCache;

    @Scheduled(fixedDelay = SYNC_DELAY_TIME)
    public void syncNicknameHistory() {
        log.info("sync nickname history");
        Set<String> adjectives = usedNicknameCache.getUsedAdjective();
        adjectives.forEach(e -> {
            Set<String> usedNounWithAdjective = nicknameHistoryRepository.findByAdjective(e).stream()
                    .map(NicknameHistory::getNoun)
                    .collect(Collectors.toSet());

            usedNicknameCache.getUsedNounWithAdjective(e).stream()
                    .filter(caching -> !usedNounWithAdjective.contains(caching))
                    .forEach(usedNoun -> {
                        NicknameHistory nicknameHistory = NicknameHistory.builder()
                                .adjective(e)
                                .noun(usedNoun)
                                .build();

                        nicknameHistoryRepository.save(nicknameHistory);
                    });
        });
    }

    @Scheduled(fixedDelay = CLEAR_DELAY_TIME)
    public void clearCachingUsedNickname() {
        log.info("clear caching data");
        usedNicknameCache.clearCaching();
    }
}
