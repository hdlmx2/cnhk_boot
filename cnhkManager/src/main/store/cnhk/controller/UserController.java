package store.cnhk.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import store.cnhk.pojo.User;
import store.cnhk.service.UserService;

@Controller
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @RequestMapping("/getById")
    @ResponseBody
    public User getById(Integer userId) {
        return userService.getById(userId);
    }
}
