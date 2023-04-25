package passwordmanager.service;

import java.util.Map;

public interface Storage {
     /**
      * Storage abstraction decoupling the functionality of the password manager and  a storage method
      * This allows for any means of storage to be used with the application
      * keeping sensitive data seperate from application code
      *
      * */

     boolean saveUser(String userName, String password);
     boolean userExists(String userName);
     boolean verifyUserPassword(String userName, String password);
     boolean clearUsers();
     Map<String, String> intializeUsers();
}
