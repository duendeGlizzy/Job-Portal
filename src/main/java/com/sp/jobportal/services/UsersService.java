package com.sp.jobportal.services;

import com.sp.jobportal.Repositories.JobSeekerProfileRepository;
import com.sp.jobportal.Repositories.RecuiterProfileRepository;
import com.sp.jobportal.Repositories.UsersRepository;
import com.sp.jobportal.entities.JobSeekerProfile;
import com.sp.jobportal.entities.RecruiterProfile;
import com.sp.jobportal.entities.Users;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecuiterProfileRepository recuiterProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository,
                        JobSeekerProfileRepository jobSeekerProfileRepository,
                        RecuiterProfileRepository recuiterProfileRepository,
                        PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recuiterProfileRepository = recuiterProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users addNew(Users user) {
        user.setActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public Object getCurrentUserProfile() {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if(!(auth instanceof AnonymousAuthenticationToken)){
        String username = auth.getName();
        Users users = usersRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        int userId = users.getUserId();
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
            RecruiterProfile recruiterProfile = recuiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
            return recruiterProfile;
        }else{
            JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
            return jobSeekerProfile;
        }
    }
    return null;
    }


    public Users getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String username = authentication.getName();
            Users user = usersRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
            return user;
        }
        return null;

    }
}
