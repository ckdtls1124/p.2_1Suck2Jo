package org.spring.p21suck2jo.service;

import lombok.RequiredArgsConstructor;
import org.spring.p21suck2jo.dto.DeptDto;
import org.spring.p21suck2jo.dto.PoliceDto;

import org.spring.p21suck2jo.entity.DeptEntity;
import org.spring.p21suck2jo.entity.PoliceEntity;
import org.spring.p21suck2jo.repository.DeptRepository;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PoliceService {

    private final PoliceRepository policeRepository;
    private final DeptRepository deptRepository;

//    private final PasswordEncoder passwordEncoder;

    public void policeAdd(PoliceDto policeDto){
        PoliceEntity police = PoliceEntity.createOfficer(policeDto);
        PoliceEntity policeInsert=policeRepository.save(police);

        DeptEntity deptEntity = DeptEntity.deptCreate
                (policeInsert.getDept(),policeInsert.getRanks(),policeInsert.getPoliceNumber(),policeInsert);
        deptRepository.save(deptEntity);

    }

    public List<PoliceDto> policeList(){
        List<PoliceDto> policeList = new ArrayList<>();
        List<PoliceEntity> policesSearch = policeRepository.findAll();

        for(PoliceEntity polices : policesSearch){
            policeList.add(PoliceDto.officerView(polices));
        }
        return policeList;
    }

    public PoliceDto policeDetail(Long id) {
        Optional<PoliceEntity> policeIdSearch = policeRepository.findByPoliceId(id);
        return PoliceDto.officerView(policeIdSearch.get());

    }

    //회원수정(myPage)
    public void policeUpdate(PoliceDto policeDto){
     PoliceEntity police=   PoliceEntity.createOfficer(policeDto);
        policeRepository.save(police);
    }



    public void policeDelete(Long id){
        Optional<PoliceEntity> policeIdSearch=policeRepository.findByPoliceId(id);
        PoliceEntity policeEntity = policeIdSearch.get();

         policeRepository.delete(policeEntity);

    }


    public DeptDto deptDetail(Long id){
        Optional<DeptEntity> deptIdSearch = deptRepository.findByDeptId(id);
        return DeptDto.deptView(deptIdSearch.get());
    }

    public List<DeptDto> deptList(){
        List<DeptDto> deptList = new ArrayList<>();
        List<DeptEntity> deptListSearch=deptRepository.findAll();

        for (DeptEntity dept : deptListSearch){
            deptList.add(DeptDto.deptView(dept));
        }
        return deptList;
    }

    public DeptDto deptDetail2(Long id){
//        Optional<PoliceEntity> policeIdSearch = policeRepository.findByPoliceId(id);
//        PoliceEntity  policeEntity= policeIdSearch.get();
        DeptEntity deptEntity= deptRepository.findByPolice(id);
        return DeptDto.deptView(deptEntity);
    }

}
