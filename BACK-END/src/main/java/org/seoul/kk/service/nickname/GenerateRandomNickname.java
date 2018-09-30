package org.seoul.kk.service.nickname;

import com.google.common.collect.Lists;
import org.seoul.kk.cache.UsedNicknameCache;
import org.seoul.kk.dto.nickname.ResRandomNicknameDto;
import org.seoul.kk.entity.Nickname;
import org.seoul.kk.entity.constant.Classification;
import org.seoul.kk.repository.NicknameRepository;
import org.seoul.kk.service.RandomNamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GenerateRandomNickname implements RandomNamingService {

    private static final List<Nickname> nounsOfNickname = Lists.newArrayList();
    private static final List<Nickname> adjectivesOfNickname = Lists.newArrayList();

    private NicknameRepository nicknameRepository;
    private UsedNicknameCache usedNicknameCache;

    @Autowired
    public GenerateRandomNickname(NicknameRepository nicknameRepository,
                                  UsedNicknameCache usedNicknameCache) {
        this.nicknameRepository = nicknameRepository;
        this.usedNicknameCache = usedNicknameCache;

        nounsOfNickname.addAll(this.nicknameRepository.findAllByClassification(Classification.NOUN));
        adjectivesOfNickname.addAll(this.nicknameRepository.findAllByClassification(Classification.ADJECTIVE));
    }

    @Override
    public ResRandomNicknameDto generateRandomNickname() {
        String noun;
        String adjective;

        do {
            shuffleList();
            adjective = adjectivesOfNickname.get(0).getWord();
            noun = nounsOfNickname.get(0).getWord();
        } while(usedNicknameCache.isDuplicatedNickname(adjective, noun));

        return new ResRandomNicknameDto(String.format("%s %s", adjective, noun));
    }

    private void shuffleList() {
        Collections.shuffle(nounsOfNickname);
        Collections.shuffle(adjectivesOfNickname);
    }
}
