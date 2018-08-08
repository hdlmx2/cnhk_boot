package store.cnhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import store.cnhk.pojo.User;
import store.cnhk.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getById")
    @ResponseBody
    public User getById(Integer userId) {
        return userService.getById(userId);
    }
}
