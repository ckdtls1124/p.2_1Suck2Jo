package org.spring.security02.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attend")
public class AttendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attend_id")
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime checkTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime leaveTime;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

}