package xyz.qinian.esport.service;

import xyz.qinian.esport.domain.User;

public interface UserService {

    User login(User user);

    int update(User user);

    User queryUserMessage(String tel);

    int registerUser(User user);

}
