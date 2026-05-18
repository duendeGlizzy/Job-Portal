package com.sp.jobportal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPostActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobPostId;

    @ManyToOne
    @JoinColumn(name = "postedById", referencedColumnName = "userId")
    private Users postedById;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobLocationId", referencedColumnName = "Id")
    private JobLocation jobLocationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobCompanyId", referencedColumnName = "Id")
    private JobCompany jobCompanyId;

    @Transient
    private boolean isActive;

    @Transient
    private boolean isSaved;

    @Length(max = 10000)
    private String descriptionOfJob;

    private String jobType;
    private String salary;
    private String remote;
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date postedDate;
    private String jobTitle;

    public boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsSaved() {
        return isSaved;
    }
    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }





}
