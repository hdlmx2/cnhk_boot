package store.cnhk.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.cnhk.dao.UserDao;
import store.cnhk.pojo.User;
import store.cnhk.service.UserService;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User getById(Integer userId) {
        return userDao.getById(userId);
    }
}
