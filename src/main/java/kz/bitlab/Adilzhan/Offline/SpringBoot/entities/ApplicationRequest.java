package kz.bitlab.Adilzhan.Offline.SpringBoot.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_requests")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Long id;

    @Column(name = "r_userName")
    private String userName;
    @ManyToOne
    private Courses courseName;
    @Column(name = "r_commentary")
    private String commentary;
    @Column(name = "r_phone")
    private String phone;
    @Column(name = "r_handler")
    private boolean handled;

    public boolean getHandled() {
        return handled;
    }

}
