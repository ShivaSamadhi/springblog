package com.codeup.haskellspringblog.models;

import javax.persistence.*;

@Entity
@Table(name ="ad_images")
public class AdImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String path;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;
}
