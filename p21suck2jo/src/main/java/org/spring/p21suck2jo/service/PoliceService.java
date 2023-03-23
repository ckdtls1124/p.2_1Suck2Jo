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
        PoliceEntity police = PoliceService.createOfficer(policeDto);
        policeRepository.save(police);

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
     PoliceEntity police=   PoliceService.createOfficer(policeDto);
        policeRepository.save(police);
    }



    public void policeDelete(Long id){
        Optional<PoliceEntity> policeIdSearch=policeRepository.findByPoliceId(id);
        PoliceEntity policeEntity = policeIdSearch.get();

         policeRepository.delete(policeEntity);

    }


    public static PoliceEntity createOfficer(PoliceDto policeDto){ //test 끝나면 passwordEncoder

        PoliceEntity police = new PoliceEntity();
        police.setPoliceId(policeDto.getPoliceId());
//        police.setPassword(passwordEncoder.encode(policeDto.getPassword()));
        police.setPassword(policeDto.getPassword());
        police.setPoliceName(policeDto.getPoliceName());
        police.setEmail(policeDto.getEmail());
        police.setPoliceNumber(policeDto.getPoliceNumber());
        police.setRanks(policeDto.getRanks());
        police.setZip_code(policeDto.getZip_code());
        police.setPoliceAddress(policeDto.getPoliceAddress());
        police.setDetailAddress(policeDto.getDetailAddress());
        police.setPolicePhone(policeDto.getPolicePhone());
        police.setDept(policeDto.getDept());
        return police;
    }

}
