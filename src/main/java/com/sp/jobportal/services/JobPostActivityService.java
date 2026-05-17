package com.sp.jobportal.services;

import com.sp.jobportal.Repositories.JobPostActivityRepository;
import com.sp.jobportal.entities.JobPostActivity;
import org.springframework.stereotype.Service;

@Service
public class JobPostActivityService {
    private final JobPostActivityRepository jobPostActivityRepository;

    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity) {

        return jobPostActivityRepository.save(jobPostActivity);

    }

}
