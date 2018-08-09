package store.cnhk.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    private static final Logger logger = LogManager.getLogger(WelcomeController.class.getName());

    @RequestMapping("/cnhkManager")
    public void cnhkManager(String userId) {


    }
}
