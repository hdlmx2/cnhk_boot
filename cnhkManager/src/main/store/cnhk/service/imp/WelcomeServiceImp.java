package store.cnhk.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.cnhk.dao.UserDao;
import store.cnhk.pojo.User;
import store.cnhk.service.WelcomeService;

@Service
public class WelcomeServiceImp implements WelcomeService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User getUser(Integer userId) {
        return userDao.getById(userId);
    }
}
