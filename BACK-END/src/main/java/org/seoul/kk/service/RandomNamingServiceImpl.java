package org.seoul.kk.service;

import org.seoul.kk.dto.nickname.ResRandomNicknameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RandomNamingServiceImpl implements RandomNamingService {

    @Autowired
    @Qualifier(value = "generateRandomNickname")
    private RandomNamingService randomNamingService;

    @Override
    public ResRandomNicknameDto generateRandomNickname() {
        return randomNamingService.generateRandomNickname();
    }
}
