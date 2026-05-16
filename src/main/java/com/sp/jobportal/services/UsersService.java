package com.sp.jobportal.services;

import com.sp.jobportal.Repositories.JobSeekerProfileRepository;
import com.sp.jobportal.Repositories.RecuiterProfileRepository;
import com.sp.jobportal.Repositories.UsersRepository;
import com.sp.jobportal.entities.JobSeekerProfile;
import com.sp.jobportal.entities.RecruiterProfile;
import com.sp.jobportal.entities.Users;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecuiterProfileRepository recuiterProfileRepository;

    public UsersService(UsersRepository usersRepository,
                        JobSeekerProfileRepository jobSeekerProfileRepository,
                        RecuiterProfileRepository recuiterProfileRepository) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recuiterProfileRepository = recuiterProfileRepository;
    }

    public Users addNew(Users user) {
        user.setActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        Users savedUser = usersRepository.save(user);
        int userTypeId = user.getUserTypeId().getUserTypeId();

        if(userTypeId == 1){
            recuiterProfileRepository.save(new RecruiterProfile(savedUser));
        }
        else{
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }


        return savedUser;
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

}
