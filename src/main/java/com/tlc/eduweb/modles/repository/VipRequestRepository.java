package com.tlc.eduweb.modles.repository;
import com.tlc.eduweb.modles.entity.VipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VipRequestRepository extends JpaRepository<VipRequest,Long>{
}
