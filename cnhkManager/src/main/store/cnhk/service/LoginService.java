package store.cnhk.service;

import store.cnhk.pojo.User;

public interface LoginService {
    User login(String userName, String password);
}
