package kz.bitlab.Adilzhan.Offline.SpringBoot.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_operators")
@Data
public class Operators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "department")
    private String department;
}
