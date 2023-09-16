package pl.pb.storageproject.model;


import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;

@Entity
@Table(name = "SHARE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idFrom;

    @Column(nullable = false)
    private Long idTo;


    @OneToOne(cascade = CascadeType.MERGE)
    private Information information;

    public Share(Long idFrom, Long idTo, Information information) {
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.information = information;
    }
}
