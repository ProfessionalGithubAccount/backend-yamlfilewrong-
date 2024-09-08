package com.capstone.assignmentmicroservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    @Id
    private Integer assignmentId;
    private Integer professorId;
    private Integer courseId;
    @Lob
    @Column(name = "assignment_file", columnDefinition = "MEDIUMBLOB")
    private byte[] assignmentFile;
    private String title;
    private String fileName;
    private Date deadline;
}
