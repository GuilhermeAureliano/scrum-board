package com.dev.scrumboard.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PARTNER_INSTITUTION", nullable = false)
    private String partnerInstitution;

    @ManyToOne(optional = true)
    @JoinColumn(name = "SCRUM_MASTER_ID")
    private User responsibleScrumMaster;
}
