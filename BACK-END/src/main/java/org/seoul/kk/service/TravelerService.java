package org.seoul.kk.service;

import org.seoul.kk.dto.traveler.RegisterTravelerDto;
import org.seoul.kk.entity.Traveler;
import org.seoul.kk.exception.NotFoundTraveler;

public interface TravelerService {

    Traveler getTravelerById(Long id) throws NotFoundTraveler;
    Traveler getTravelerByUuid(String uuid) throws NotFoundTraveler;
    Traveler registerTraveler(RegisterTravelerDto requestBody, String uuid);

}
