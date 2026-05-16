package com.sp.jobportal.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users_type")
public class UsersType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userTypeId;

    private String userTypeName;

    @OneToMany(targetEntity = Users.class,
    mappedBy = "userTypeId", cascade = CascadeType.ALL)
    private List<Users> users;

}
