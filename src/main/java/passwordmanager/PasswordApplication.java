package passwordmanager;

import passwordmanager.service.PasswordManager;
import passwordmanager.service.impl.PasswordManagerImpl;

public class PasswordApplication {
    public static void main(String[] args) {
        PasswordManager passwordManager = new PasswordManagerImpl();
        passwordManager.initializeUsers().entrySet().forEach( u-> passwordManager.addUser(u.getKey(), u.getValue()));
    }
}
