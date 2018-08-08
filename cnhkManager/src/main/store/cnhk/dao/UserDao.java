package store.cnhk.dao;

import store.cnhk.pojo.User;

public interface UserDao {
    User getById(Integer userId);
}
