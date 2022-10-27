package com.zerobase.weather.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Memo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String text;

    @CreatedDate
    private String createdAt;

    @LastModifiedDate
    private String updatedAt;
}
