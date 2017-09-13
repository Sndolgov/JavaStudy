package com.sergey;

import java.util.List;

/**
 * Created by Сергей on 23.08.2017.
 */
public interface UserService {
    void addUser(User user);
    void updateUser (User user);
    void removeUser (int id);
    User getUserById(int id);
    List<User> listUser();
}
