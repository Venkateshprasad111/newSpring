package com.prasad.news.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "new_type")
public class NewsType {

    @Id
    @Column(name = "news_type")
    private String newsType;


    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date createdOn;
    @ManyToOne
    @JoinColumn(name = "created_by",
            referencedColumnName = "user_id")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "updated_by",
            referencedColumnName = "user_id")
    private User updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Date updatedOn;





}
