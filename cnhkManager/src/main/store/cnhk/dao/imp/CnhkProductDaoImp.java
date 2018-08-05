package store.cnhk.dao.imp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import store.cnhk.dao.CnhkProductDao;
import store.cnhk.pojo.CnhkProduct;

import java.util.List;

@Repository
public class CnhkProductDaoImp implements CnhkProductDao {
    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<CnhkProduct> list() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from CnhkProduct ";
        Query query = session.createQuery(hql);
        return query.list();
    }
}
