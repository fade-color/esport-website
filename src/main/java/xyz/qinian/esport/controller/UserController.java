package xyz.qinian.esport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.qinian.esport.domain.Msg;
import xyz.qinian.esport.domain.User;
import xyz.qinian.esport.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/update/{tel}")
    public Msg updateUserMessage(@RequestParam("password") String password, @RequestParam("user_name") String username,
                                 @RequestParam("sex") String sex, @RequestParam("head_path") String headPath,
                                 @PathVariable("tel") String tel) {
        User user = new User(tel, password, username, sex, headPath);
        int result = userService.update(user);
        if (result == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @ResponseBody
    @RequestMapping("/login")
    public Msg login(@RequestParam("tel") String tel, @RequestParam("password") String password) {
        User user = new User();
        user.setTel(tel);
        user.setPassword(password);
        User result = userService.login(user);
        if (result != null) {
            result.setTel(user.getTel());
            return Msg.success().add("user", result);
        }
        return Msg.fail();
    }

}
