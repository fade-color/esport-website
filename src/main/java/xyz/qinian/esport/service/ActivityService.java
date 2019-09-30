package xyz.qinian.esport.service;

import xyz.qinian.esport.domain.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> selectAll();

    int insertSelective(Activity record);

}
