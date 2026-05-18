package com.sp.jobportal.Repositories;

import com.sp.jobportal.entities.JobPostActivity;
import com.sp.jobportal.entities.JobSeekerProfile;
import com.sp.jobportal.entities.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave,Integer>{

    List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);

    List<JobSeekerSave> findByJob(JobPostActivity job);
}
