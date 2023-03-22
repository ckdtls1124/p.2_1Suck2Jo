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

// =================================부서 테스트중======================
//    public List<DeptDto> deptList(){
//        List<DeptDto> deptDtoList = new ArrayList<>();
//       List<DeptEntity> list= deptRepository.findAll();
//
//       for(DeptEntity deptEntity : list){
//
//           deptDtoList.add(DeptDto.deptView2(deptEntity,deptEntity.getPoliceList().size()));
//       }
//       return deptDtoList;
//    }
//
//    public DeptDto deptId(Long id){
//      DeptEntity dept=deptRepository.findByDeptId(id);
//
//      return DeptDto.deptView2(dept,dept.getPoliceList().size());
//
//    }



}
