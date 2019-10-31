package com.tlc.eduweb.controller;

import com.tlc.eduweb.modles.entity.Schedule;
import com.tlc.eduweb.modles.repository.ScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    ScheduleDao scheduleDao;

    @RequestMapping("/courseBetweenWeek/{id}")
    @ResponseBody
    public List<Schedule> test(@PathVariable Integer id, @RequestParam("dateStart") Date dateStart, @RequestParam("dateEnd") Date dateEnd, @RequestParam("courseType") String courseType) {
        List<Schedule> byDateBetween = scheduleDao.findByClassIdAndDateBetweenAndType(id, dateStart, dateEnd, courseType);
        return byDateBetween;
    }

    @RequestMapping("/getClassId")
    @ResponseBody
    public List<Object> getClassId() {
        List<Object> classIdList = scheduleDao.getClassId();
        return classIdList;
    }

}
