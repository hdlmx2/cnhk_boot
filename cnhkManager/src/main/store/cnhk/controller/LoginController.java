package store.cnhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import store.cnhk.pojo.User;
import store.cnhk.service.LoginService;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    public String login(String userName, String password) {
        User user = loginService.login(userName, password);
        return null;
    }
}
