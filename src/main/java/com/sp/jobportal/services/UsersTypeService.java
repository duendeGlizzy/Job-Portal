package com.sp.jobportal.services;

import com.sp.jobportal.Repositories.UsersTypeRepository;
import com.sp.jobportal.entities.UsersType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeService {

    private final UsersTypeRepository usersTypeRepository;

    public UsersTypeService(UsersTypeRepository usersTypeRepository) {
        this.usersTypeRepository = usersTypeRepository;
    }

    public List<UsersType> getAllUsersTypes() {
        return usersTypeRepository.findAll();
    }




}
