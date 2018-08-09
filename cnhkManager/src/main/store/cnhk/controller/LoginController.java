package store.cnhk.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import store.cnhk.pojo.User;
import store.cnhk.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class.getName());

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/page/login/login.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(String userName, String password, HttpServletRequest request) {
        User user = loginService.login(userName, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", user.getName());
            Map<String, Object> result = new HashMap<>();
            result.put("flag", true);
            result.put("userId", user.getUserId());
            return result;
        } else {
            Map<String, Object> result = new HashMap<>();
            result.put("flag", false);
            return result;
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userName", null);
        return "/page/login/login.html";
    }
}
