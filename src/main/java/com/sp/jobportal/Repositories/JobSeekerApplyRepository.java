package com.sp.jobportal.Repositories;

import com.sp.jobportal.entities.JobPostActivity;
import com.sp.jobportal.entities.JobSeekerApply;
import com.sp.jobportal.entities.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply,Integer> {

    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);

    List<JobSeekerApply> findByJob(JobPostActivity job);



}
