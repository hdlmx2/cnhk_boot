package store.cnhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import store.cnhk.pojo.CnhkProduct;
import store.cnhk.service.CnhkProductService;

import java.util.List;

@Controller
public class CnhkProductController {
    @Autowired
    private CnhkProductService cnhkProductService;

    @RequestMapping("/cnhkProduct")
    @ResponseBody
    public List<CnhkProduct> list() {
        return cnhkProductService.list();
    }

}
