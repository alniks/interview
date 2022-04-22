package com.alniks.interview;

import javax.inject.Inject;

public class MessageListener {
    
    @Inject
    private UserDao userDao;
    
    public void onUserCreated(Long id) {
        User user = userDao.getUserFromDB(id);
        System.out.println("=> new user created: " + user.getName());
    }
    
}
