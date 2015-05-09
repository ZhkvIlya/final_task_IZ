package com.kzn.itis.db.repositories;

import com.kzn.itis.db.model.UserF;

public interface UserFRepository {

    UserF addUser(UserF event);
    UserF removeUser(int id);
    void userUpdate(int id,UserF event);
    UserF getUser(int id);
    long getCount();
}
