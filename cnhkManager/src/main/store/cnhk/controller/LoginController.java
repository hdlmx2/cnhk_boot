package store.cnhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import store.cnhk.pojo.User;
import store.cnhk.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/page/login/login.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String password, HttpServletRequest request) {
        User user = loginService.login(userName, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", user.getName());
            return "/index.html";
        } else {
            return "/page/login/login.html";
        }

    }

    @RequestMapping(value = "logOut", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userName", null);
        return "page/login/login.html";
    }
}
