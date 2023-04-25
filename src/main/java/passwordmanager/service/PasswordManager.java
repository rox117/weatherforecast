package passwordmanager.service;

import passwordmanager.exception.UserException;

import java.util.Map;

public interface PasswordManager {
    boolean addUser(String userName, String password);
    boolean clearUsers();
    Map<String,String> initializeUsers();
    boolean changePassword(String userName, String oldPassword, String newPassword) throws UserException;
    boolean verifyUser(String userName, String password);
}
