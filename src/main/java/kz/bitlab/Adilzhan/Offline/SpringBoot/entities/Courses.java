package kz.bitlab.Adilzhan.Offline.SpringBoot.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_courses")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_course_id")
    private Long id;
    @Column(name = "t_course_name")
    private String name;
    @Column(name = "t_courses_description")
    private String description;
    @Column(name = "t_courses_price")
    private int price;

    public Courses(Long id) {
        this.id = id;
    }
}
