package com.sp.jobportal.services;

import com.sp.jobportal.Repositories.JobSeekerProfileRepository;
import com.sp.jobportal.entities.JobSeekerProfile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
    }


    public Optional<JobSeekerProfile> getOne(int id) {

        return jobSeekerProfileRepository.findById(id);

    }


    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }
}
