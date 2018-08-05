package store.cnhk.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.cnhk.dao.CnhkProductDao;
import store.cnhk.pojo.CnhkProduct;
import store.cnhk.service.CnhkProductService;

import java.util.List;

@Service
public class CnhkProductServiceImp implements CnhkProductService {
    @Autowired
    private CnhkProductDao cnhkProductDao;

    @Override
    @Transactional
    public List<CnhkProduct> list() {
        return cnhkProductDao.list();
    }
}
