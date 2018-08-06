package store.cnhk.dao.imp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import store.cnhk.dao.LoginDao;
import store.cnhk.pojo.User;

import java.util.List;

@Repository
public class LoginDaoImp implements LoginDao {
    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public User login(String userName, String password) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from User u where u.userName=:userName and u.userPassword=:password";
        Query query = session.createQuery(hql);
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        List<User> user = query.list();
        if (user != null && user.size() > 0) {
            return user.get(0);
        } else {
            return null;
        }

    }
}
