package store.cnhk.dao;

import store.cnhk.pojo.User;

public interface LoginDao {
    User login(String userName,String password);

}
