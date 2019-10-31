package com.tlc.eduweb.modles.repository;

import com.tlc.eduweb.modles.entity.LeaveDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "leaveDetail", path = "leaveDetails")
public interface LeaveDetailDao extends JpaRepository<LeaveDetail, Integer> {
}
