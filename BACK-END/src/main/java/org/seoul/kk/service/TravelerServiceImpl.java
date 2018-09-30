package org.seoul.kk.service;

import org.seoul.kk.dto.traveler.RegisterTravelerDto;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.entity.constant.TravelProperty;
import org.seoul.kk.exception.NotFoundTraveler;
import org.seoul.kk.repository.TravelerRepository;
import org.springframework.stereotype.Service;

@Service
public class TravelerServiceImpl implements TravelerService {

    private TravelerRepository travelerRepository;

    public TravelerServiceImpl(TravelerRepository travelerRepository) {
        this.travelerRepository = travelerRepository;
    }

    @Override
    public Traveler getTravelerById(Long id) throws NotFoundTraveler {
        return travelerRepository.findById(id).orElseThrow(NotFoundTraveler::new);
    }

    @Override
    public Traveler getTravelerByUuid(String uuid) throws NotFoundTraveler {
        return travelerRepository.findByUuid(uuid).orElseThrow(NotFoundTraveler::new);
    }

    @Override
    public Traveler registerTraveler(RegisterTravelerDto requestBody, String uuid) throws NotFoundTraveler {
        Traveler traveler = Traveler.builder()
                .uuid(uuid)
                .nickname(requestBody.getNickname())
                .property(TravelProperty.valueOf(requestBody.getProperty()))
                .build();

        return travelerRepository.save(traveler);
    }
}
