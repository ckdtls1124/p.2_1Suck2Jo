package org.spring.p21suck2jo.memorandumRepository;

import org.spring.p21suck2jo.entity.MemorandumFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MemorandumFileRepository extends JpaRepository<MemorandumFileEntity, Long> {
}
