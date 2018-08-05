package store.cnhk.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.cnhk.dao.LoginDao;
import store.cnhk.pojo.User;
import store.cnhk.service.LoginService;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    @Transactional
    public User login(String userName, String password) {
        return loginDao.login(userName, password);
    }
}
