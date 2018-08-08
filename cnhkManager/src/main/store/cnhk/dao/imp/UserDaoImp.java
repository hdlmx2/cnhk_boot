package store.cnhk.dao.imp;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import store.cnhk.dao.UserDao;
import store.cnhk.pojo.User;

@Repository
public class UserDaoImp implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getById(Integer userId) {
        return sessionFactory.getCurrentSession().get(User.class, userId);
    }
}
