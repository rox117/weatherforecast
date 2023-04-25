package passwordmanager.service.impl;

import passwordmanager.exception.InvalidCredentialsException;
import passwordmanager.exception.UserException;
import passwordmanager.exception.UserNotFoundException;
import passwordmanager.service.PasswordManager;
import passwordmanager.service.Storage;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class PasswordManagerImpl implements PasswordManager {
    private static final Storage userStorage = new UserStore();

    public PasswordManagerImpl() {
    }

    @Override
    public boolean addUser(String userName, String password) {
        String hashedPassword = hashPasswordAndHandleExceptions(password);
        if (hashedPassword == null){
            return false;
        }
        if (!userStorage.userExists(userName)){
            userStorage.saveUser(userName, hashedPassword);
            return true;
        }
        return false;
    }

    @Override
    public boolean clearUsers() {
        return userStorage.clearUsers();
    }

    @Override
    public Map<String, String> initializeUsers() {
        return userStorage.intializeUsers();
    }

    @Override
    public boolean changePassword(String userName, String oldPassword, String newPassword) throws UserException {
        String encryptedOldPassword = hashPasswordAndHandleExceptions(oldPassword);
        String encryptedNewPassword = hashPasswordAndHandleExceptions(newPassword);
        if (encryptedNewPassword == null || encryptedNewPassword == null){
            return false;
        }
        if (!userStorage.userExists(userName)) {
            throw  new UserNotFoundException("The specified user could not be found");
        }
        if (!userStorage.verifyUserPassword(userName, encryptedOldPassword)) {
            throw new InvalidCredentialsException("The username or password is incorrect");
        }
        userStorage.saveUser(userName, encryptedNewPassword);
        return true;
    }

    @Override
    public boolean verifyUser(String userName, String password) {
        return userStorage.verifyUserPassword(userName, password);
    }

    // Seperated exception handling from the hash function following Single Level of abstraction principle
    //The exceptions would normally be handled  and returned in a  form that would limit information while still providing enough for debugging
    //for demo purposes this was done
    public String hashPasswordAndHandleExceptions(String password){
        String hash = null;
        try {
            hash = hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return hash;
    }


    public String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String keyHashAlgorithm = "PBKDF2WithHmacSHA512";
        int iterations = 600000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf;
        byte[] hash;
            skf = SecretKeyFactory.getInstance(keyHashAlgorithm);
            hash = skf.generateSecret(spec).getEncoded();

        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private  byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private  String toHex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
