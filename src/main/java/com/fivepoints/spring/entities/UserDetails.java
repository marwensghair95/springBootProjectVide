package com.fivepoints.spring.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long id;

    @NonNull
    @Column(name = "age")
    private int age;
    @NonNull
    @Column(name = "birth_date")
    private Date birthDate;
    @NonNull
    @Column(name = "github_profile_link")
    private String githubProfileLink;
    @NonNull
    @Column(name = "linkedin_profile_link")
    private String linkedinProfileLink;

    // OneTOOne Relations
//    @OneToOne(mappedBy = "details")
//    private User user;

    @Setter(value = AccessLevel.NONE)
    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Setter(value = AccessLevel.NONE)
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
}
