package com.tlc.eduweb.modles.repository;

import com.tlc.eduweb.modles.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorsRepository extends JpaRepository<Administrator,Long> {
}