package com.sp.jobportal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_seeker_profile")
public class JobSeekerProfile {

    @Id
    private int userAccountid;

    @OneToOne
    @JoinColumn(name="user_account_id")
    @MapsId
    private Users userId;

    private String city;
    private String country;
    private String employmentType;
    private String firstName;
    private String lastName;
    @Column(nullable = true, length = 64)
    private String profilePhoto;
    private String resume;
    private String state;
    private String workAuthorization;

    @OneToMany(targetEntity = Skills.class, cascade = CascadeType.ALL,
    mappedBy = "jobSeekerProfile")
    private List<Skills> skills;



    public JobSeekerProfile(Users user) {
        this.userId = user;
    }


}
