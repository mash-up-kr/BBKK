package org.seoul.kk.cache;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.seoul.kk.entity.history.NicknameHistory;
import org.seoul.kk.repository.NicknameHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsedNicknameCache {

    private final Map<String, Set<String>> usedNickname = Maps.newConcurrentMap();

    @Autowired
    private NicknameHistoryRepository nicknameHistoryRepository;

    private UsedNicknameCache() { }

    private void putUseNicknameIfAbsentAdjective(String adjective, String noun) {
        usedNickname.putIfAbsent(adjective, Sets.newHashSet(noun));
    }

    public void putUseNickname(String adjective, String noun) {
        if (!usedNickname.containsKey(adjective)) {
            putUseNicknameIfAbsentAdjective(adjective, noun);
        }

        usedNickname.get(adjective).add(noun);
    }

    public Set<String> getUsedNounWithAdjective(String adjective) {

        return usedNickname.getOrDefault(adjective, null);
    }

    public Set<String> getUsedAdjective() {

        return usedNickname.keySet();
    }

    public void clearCaching() {
        usedNickname.clear();
    }

    //TODO 테스트 필요
    public boolean isDuplicatedNickname(String adjective, String noun) {
        if (!usedNickname.containsKey(adjective)) {
            List<NicknameHistory> histories = nicknameHistoryRepository.findByAdjective(adjective);
            if (!histories.isEmpty()) {
                usedNickname.put(adjective, histories.stream()
                        .map(NicknameHistory::getNoun)
                        .collect(Collectors.toSet()));
            } else {
                usedNickname.put(adjective, Sets.newHashSet(noun));
            }

            return false;
        }

        if (!usedNickname.get(adjective).contains(noun)) {
            usedNickname.get(adjective).add(noun);
            return false;
        }

        return true;
    }
}
