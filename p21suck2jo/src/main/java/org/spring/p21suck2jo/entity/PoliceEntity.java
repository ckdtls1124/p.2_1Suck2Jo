package org.spring.p21suck2jo.entity;

import lombok.*;
import org.spring.p21suck2jo.dto.PoliceDto;
import org.spring.p21suck2jo.role.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

//import org.spring.p21suck2jo.convert.PoliceConvert;
//import org.spring.p21suck2jo.dto.PoliceDto;
//import org.spring.p21suck2jo.dto.PoliceDto;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "police_officer")
public class PoliceEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "police_id")
    private Long policeId;

    @Column(nullable = false, unique = true)
    private String email;
//    @Column(nullable = true)
    private String password;


    private String policeName;
    @Column(nullable = false, unique = true)
    private int policeNumber; //사원번호
    private String ranks; //직책 <- table

    @Enumerated(EnumType.STRING)
    private Role role; //권한

    private String zip_code;
    private String policeAddress;
    private String DetailAddress;
    private String policePhone;

    @ManyToOne
    @JoinColumn(name ="dept_id")
    private DeptEntity dept;

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<BoardEntity> boardList = new ArrayList<>();
    
    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<ReplyEntity> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<AttendEntity> attendList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<CalendarEntity> calendarList = new ArrayList<>();

    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<MemorandumEntity> moMemorandumList = new ArrayList<>();


    @OneToMany(mappedBy = "eventJoinPolice",cascade = CascadeType.ALL)
    private List<EventEntity> EventList = new ArrayList<>();


    @OneToMany(mappedBy = "police",cascade = CascadeType.ALL)
    private List<InquiryEntity> inquiryList = new ArrayList<>();

//    public static PoliceEntity createOfficer(PoliceDto policeDto, PasswordEncoder passwordEncoder){ //test 끝나면 passwordEncoder
//
//        PoliceEntity police = new PoliceEntity();
//
//        police.setPoliceId(policeDto.getPoliceId());
//        police.setEmail(policeDto.getEmail());
//        police.setPassword(passwordEncoder.encode(policeDto.getPassword()));
//        police.setPoliceName(policeDto.getPoliceName());
//        police.setPoliceNumber(policeDto.getPoliceNumber());
//        police.setRanks(policeDto.getRanks());
//        police.setRole(Role.MEMBER);
//        police.setZip_code(policeDto.getZip_code());
//        police.setPoliceAddress(policeDto.getPoliceAddress());
//        police.setDetailAddress(policeDto.getDetailAddress());
//        police.setPolicePhone(policeDto.getPolicePhone());
//        police.setCreateTime(policeDto.getCreateTime());
//        police.setDept(policeDto.getDept());
//        return police;
//    }


    public static PoliceEntity pwUpdateEntity(PoliceDto policeDto, PasswordEncoder passwordEncoder) {
        PoliceEntity policeEntity = new PoliceEntity();

        policeEntity.setPoliceId(policeDto.getPoliceId());
        policeEntity.setEmail(policeDto.getEmail());
        policeEntity.setPassword(passwordEncoder.encode(policeDto.getPassword()));
        policeEntity.setPoliceNumber(policeDto.getPoliceNumber());
        policeEntity.setPoliceName(policeDto.getPoliceName());
        policeEntity.setRanks(policeDto.getRanks());
        policeEntity.setRole(Role.MEMBER);
//        policeEntity.setCreateTime(policeDto.getCreateTime());
//        policeEntity.setUpdateTime(policeDto.getUpdateTime());
        return policeEntity;
    }
}