package xyz.qinian.esport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import xyz.qinian.esport.domain.Msg;
import xyz.qinian.esport.domain.User;
import xyz.qinian.esport.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    public static final int HEAD_PATH = 1;

    public static final int ACTIVITY_PATH = 1;


    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @ResponseBody
    @RequestMapping("/update")
    public Msg updateUserMessage(@RequestParam("password") String password, @RequestParam("user_name") String username,
                                 @RequestParam("sex") String sex, @RequestParam("head_path") String headPath) {
        String tel = ((User)request.getSession().getAttribute("user")).getTel();
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
            result.setTel(tel);
            HttpSession session = request.getSession();
            session.setAttribute("user", result);
            return Msg.success().add("user", result);
        }
        return Msg.fail();
    }

    @ResponseBody
    @RequestMapping("/query")
    public Msg queryUserMessage(@RequestParam("tel") String userTel) {
        User user1 = userService.queryUserMessage(userTel);
        if (user1 != null) {
            return Msg.success().add("user",user1);
        }
        return Msg.fail();
    }

    @RequestMapping("/verifyLoginInfo")
    public void verifyLoginInfo(@RequestParam("tel") String tel, @RequestParam("password") String password) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            login(tel, password);
        }
    }

    @ResponseBody
    @RequestMapping("/register")
    public Msg registerUser(@RequestParam("tel") String tel, @RequestParam("password") String password) {
        User user = new User();
        user.setTel(tel);
        user.setPassword(password);
        try {
            int i = userService.registerUser(user);
            if (i == 1) {
                return Msg.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/uploadactivity", method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload1(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest rq = (MultipartHttpServletRequest) request;
        MultipartFile uploadFile = rq.getFileMap().get("file");
        String originalFilename = uploadFile.getOriginalFilename();
        return savePic(uploadFile.getInputStream(), originalFilename, ACTIVITY_PATH);
    }

    @RequestMapping(value = "/uploadusericon", method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload2(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest rq = (MultipartHttpServletRequest) request;
        MultipartFile uploadFile = rq.getFileMap().get("file");
        String originalFilename = uploadFile.getOriginalFilename();
        return savePic(uploadFile.getInputStream(), originalFilename, HEAD_PATH);
    }

    private String savePic(InputStream inputStream, String originalFilename, int type) {
        OutputStream os = null;
        String fileName = null;
        try {
            String path, imagePrefix;
            File fDir=new File(File.separator);
            if (type == ACTIVITY_PATH) {
                path = "root"+File.separator+"esport"+File.separator+"activity_images";
                imagePrefix = "activity_image_";
            } else {
                path = "root"+File.separator+"esport"+File.separator+"head_icon";
                imagePrefix = "head_icon_";
            }
            // 1k的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;

            File tempFile = new File(fDir,path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            Date date = new Date();
            String[] split = originalFilename.split("\\.");
            String imageSuffix = split[split.length - 1];
            fileName = imagePrefix + date.getTime() + "." + imageSuffix;
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭所有连接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

}
