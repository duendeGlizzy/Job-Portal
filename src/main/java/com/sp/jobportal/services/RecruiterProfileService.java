package com.sp.jobportal.services;

import com.sp.jobportal.Repositories.RecuiterProfileRepository;
import com.sp.jobportal.entities.RecruiterProfile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {

    private final RecuiterProfileRepository recuiterProfileRepository;

    public RecruiterProfileService(RecuiterProfileRepository recuiterProfileRepository) {
        this.recuiterProfileRepository = recuiterProfileRepository;
    }

    public Optional<RecruiterProfile> getOne(Integer id){
        return recuiterProfileRepository.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile){
        return recuiterProfileRepository.save(recruiterProfile);
    }
}
