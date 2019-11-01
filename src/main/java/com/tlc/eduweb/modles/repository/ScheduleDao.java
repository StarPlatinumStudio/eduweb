package com.tlc.eduweb.modles.repository;

import com.tlc.eduweb.modles.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "schedule", path = "schedules")
public interface ScheduleDao extends JpaRepository<Schedule, Integer> {

    List<Schedule> findByClassIdAndDateBetweenAndTypeOrderByDateAscTimeStartAsc(Integer id, Date dateStart, Date dateEnd, String type);

    @Query("SELECT DISTINCT classId FROM Schedule")
    List<Object> getClassId();
}
