package store.cnhk.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import store.cnhk.pojo.CnhkProduct;
import store.cnhk.service.CnhkProductService;

import java.util.List;

@Controller
public class CnhkProductController {
    private static final Logger logger = LogManager.getLogger(CnhkProductController.class.getName());

    @Autowired
    private CnhkProductService cnhkProductService;

    @RequestMapping("/cnhkProduct")
    @ResponseBody
    public List<CnhkProduct> list() {
        return cnhkProductService.list();
    }

}
