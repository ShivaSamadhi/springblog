package com.codeup.haskellspringblog.models;


import org.apache.catalina.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    @OneToOne
    private User owner;
    //Mapping a one-to-one relationship with JPA is as easy as adding the @OneToOne annotation. Following our example, ads belong to a single User.

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ad")
    private List<AdImage> images;
    //A many-to-one association and a one-to-many association are the same association seen from the perspective of the owning and subordinate entities, respectively. Going back to our Ad class, an ad can have several images; we can map this as a bidirectional association

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="ads_categories",
            joinColumns={@JoinColumn(name="ad_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")}
    )
    private List<AdCategory> categories;

}
