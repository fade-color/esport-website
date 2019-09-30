package xyz.qinian.esport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.qinian.esport.domain.Activity;
import xyz.qinian.esport.mapper.ActivityMapper;
import xyz.qinian.esport.service.ActivityService;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Override
    public List<Activity> selectAll() {
        return activityMapper.selectAll();
    }

    @Override
    public int insertSelective(Activity record) {
        return activityMapper.insertSelective(record);
    }
}
