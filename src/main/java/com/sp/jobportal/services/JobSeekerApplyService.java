package com.sp.jobportal.services;

import com.sp.jobportal.Repositories.JobSeekerApplyRepository;
import com.sp.jobportal.entities.JobPostActivity;
import com.sp.jobportal.entities.JobSeekerApply;
import com.sp.jobportal.entities.JobSeekerProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerApplyService {

    private final JobSeekerApplyRepository jobSeekerApplyRepository;

    public JobSeekerApplyService(JobSeekerApplyRepository jsRepository) {
        this.jobSeekerApplyRepository = jsRepository;
    }

    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId){
        return jobSeekerApplyRepository.findByUserId(userAccountId);
    }

    public List<JobSeekerApply> getCandidates(JobPostActivity job){
        return jobSeekerApplyRepository.findByJob(job);
    }


    public void addNew(JobSeekerApply jobSeekerApply) {

        jobSeekerApplyRepository.save(jobSeekerApply);

    }
}
