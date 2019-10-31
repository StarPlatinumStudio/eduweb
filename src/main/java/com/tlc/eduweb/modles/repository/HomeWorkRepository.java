package com.tlc.eduweb.modles.repository;

import com.tlc.eduweb.modles.entity.HomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeWorkRepository extends JpaRepository<HomeWork,Long> {
}