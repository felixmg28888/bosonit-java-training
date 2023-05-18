package com.bosonit.formacion.block1102uploaddownloadfiles.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    @CreationTimestamp
    private Date uploadDate;
    private String type;
    @Lob
    private byte[] bytes;

}
