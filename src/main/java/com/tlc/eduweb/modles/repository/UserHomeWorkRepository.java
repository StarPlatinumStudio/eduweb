package com.tlc.eduweb.modles.repository;

import com.tlc.eduweb.modles.entity.UserHomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHomeWorkRepository extends JpaRepository<UserHomeWork,Long> {
}