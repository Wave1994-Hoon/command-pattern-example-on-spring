package com.hoon.commandpattern.project.model;

import lombok.*;
import javax.persistence.*;


@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
public class Project {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProjectStatusType status;
}
