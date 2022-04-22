package com.alniks.interview;

import java.util.Map;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * service creates a user in DB and sends a command for further processing
 */
public class UserService {
    
    @Inject
    private UserDao userDao;
    @Inject
    private MessageSender messageSender;
    
    @Transactional
    public void addUser(Map<String, Object> details) {
        User user = User.fromMap(details);
        userDao.storeInDb(user);
        messageSender.sendUserCreatedMessage(user.getId());
    }
    
}
