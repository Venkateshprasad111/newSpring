package com.prasad.news.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Component
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private UUID userId= UUID.randomUUID();

    @Column(name = "user_name")
    private String userName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date createdOn;
    @ManyToOne
    @JoinColumn(name = "created_by",
            referencedColumnName = "user_id")
    private User createdBy=null;
    @ManyToOne
    @JoinColumn(name = "updated_by",
            referencedColumnName = "user_id")
    private User updatedBy=null;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Date updatedOn;




}
