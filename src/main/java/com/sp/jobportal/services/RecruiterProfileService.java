package com.sp.jobportal.services;

import com.sp.jobportal.Repositories.RecuiterProfileRepository;
import com.sp.jobportal.Repositories.UsersRepository;
import com.sp.jobportal.entities.RecruiterProfile;
import com.sp.jobportal.entities.Users;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {

    private final RecuiterProfileRepository recuiterProfileRepository;
    private final UsersRepository usersRepository;

    public RecruiterProfileService(RecuiterProfileRepository recuiterProfileRepository,
                                   UsersRepository usersRepository) {
        this.recuiterProfileRepository = recuiterProfileRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<RecruiterProfile> getOne(Integer id){
        return recuiterProfileRepository.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile){
        return recuiterProfileRepository.save(recruiterProfile);
    }

    public RecruiterProfile getCurrentRecruiterProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUserName = authentication.getName();
            Users user = usersRepository.findByEmail(currentUserName).orElseThrow(()->new RuntimeException("User not found"));

            Optional<RecruiterProfile>  recruiterProfile = getOne(user.getUserId());
            return recruiterProfile.orElse(null);
        }else return null;
    }
}
