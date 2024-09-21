package com.example.lesson7.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Books {
    @Id
//    @SequenceGenerator(
//            name = "book_iq_sequence",
//            sequenceName = "book_iq_seq",
//            initialValue = 1,
//            allocationSize = 1
//    )
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)

//    @GeneratedValue(strategy = GenerationType.IDENTITY)

//    @TableGenerator(name = "users")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "users")

    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(name = "pages")
    private int page;
    @CreationTimestamp
    @Column(insertable = true, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime created_at;
}

