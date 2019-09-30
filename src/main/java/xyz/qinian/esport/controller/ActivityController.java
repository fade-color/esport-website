package xyz.qinian.esport.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.qinian.esport.domain.Activity;
import xyz.qinian.esport.domain.Msg;
import xyz.qinian.esport.domain.User;
import xyz.qinian.esport.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/activities/{pageno}")
    @ResponseBody
    public Msg getActivities(@PathVariable("pageno") int pn) {
        PageHelper.startPage(pn, 20);
        List<Activity> activities = activityService.selectAll();
        PageInfo<Activity> pageInfo = new PageInfo<>(activities);
        return Msg.success().add("activities", pageInfo);
    }

    @RequestMapping("/activity/submit")
    @ResponseBody
    public Msg submitActivity(@RequestParam("tel") String tel, @RequestParam("content") String content,
                              @RequestParam("image_src") String imageSrc, @RequestParam("location") String location) {
        Activity activity = new Activity();
        activity.setContent(content);
        activity.setImageSrc(imageSrc);
        activity.setLocation(location);
        activity.setUserTel(tel);
        activity.setTime(new Date());
        int i = activityService.insertSelective(activity);
        if (i == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

}
