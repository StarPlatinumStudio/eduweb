package com.tlc.eduweb.modles.repository;

import com.tlc.eduweb.modles.entity.School;
import com.tlc.eduweb.modles.entity.TheClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheClassRepository extends JpaRepository<TheClass,Long> {
}