package com.ZooFood.Diner_David_ZooFood_CaseStudy.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    //Many useres can be multiple roles
    @ManyToMany(mappedBy = "roles")
    private List<User> users;



}
