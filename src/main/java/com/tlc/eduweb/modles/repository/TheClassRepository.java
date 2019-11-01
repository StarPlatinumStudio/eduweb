package com.tlc.eduweb.modles.repository;

import com.tlc.eduweb.modles.entity.School;
import com.tlc.eduweb.modles.entity.TheClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheClassRepository extends JpaRepository<TheClass,Long> {

    @Query("SELECT id FROM TheClass")
    List<Object> getClassId();

}