//last left off at implementing password counter

package vault;

import encrypt.CaesarCypher;
import encrypt.Encryptor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Vault implements VaultInterface {
    private Map<String, Map<String, String>> authenticator = new HashMap<String, Map<String, String>>();
    private Map<String, Map<String, String>> passwordManager = new HashMap<String, Map<String, String>>();

    private Encryptor encryptor = new CaesarCypher();

    public Vault(String vaultUserName, String vaultPassWord) {
        //TODO set username and password
    }

    public void newUser(String username, String password) throws InvalidUsernameException,
            InvalidPasswordException, DuplicateUserException {
        //if (username.length() >= 6 && username.length() <= 12) {
        if (true) {
            if (password.length() >= 6 && password.length() <= 15) {
                if (authenticator.get(username) == null) {
                    authenticator.put(username, new HashMap<String, String>() {{
                        String tempUUID = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
                        put(password, tempUUID);
                    }});
                } else {
                    throw new DuplicateUserException();
                }
            } else {
                throw new InvalidPasswordException();
            }
        } else {
            throw new InvalidUsernameException();
        }
    }

    public String newSite(String vaultUserName, String vaultPassword, String siteName)
            throws DuplicateSiteException, UserNotFoundException, UserLockedOutException, PasswordMismatchException, InvalidSiteException {
        String id = getUUID(vaultUserName, vaultPassword);
        String genPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
        //TODO: encrypt this gen password

        if (passwordManager.get(id).get(siteName) != null) {
            passwordManager.put(id, new HashMap<String, String>() {{
                put(siteName, encryptor.encrypt(genPassword));
            }});
            return genPassword;
        } else {
            throw new DuplicateSiteException();
        }


    }

    public String updateSite(String vaultUsername, String vaultPassword, String sitename)
            throws SiteNotFoundException, UserNotFoundException, UserLockedOutException, PasswordMismatchException {
        String newPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 15);

        if (passwordManager.get(getUUID(vaultUsername, vaultPassword)).get(sitename) != null) {
            passwordManager.get(getUUID(vaultUsername, vaultPassword)).replace(sitename, encryptor.encrypt(newPassword));
        } else {
            throw new SiteNotFoundException();
        }

        return newPassword;

    }

    public String retrieveSite(String vaultUsername, String vaultPassword, String sitename)
            throws SiteNotFoundException, UserNotFoundException, UserLockedOutException, PasswordMismatchException {
        //username and password for the vault
        String encryptedPassword = passwordManager.get(getUUID(vaultUsername, vaultPassword)).get(sitename);

        return encryptor.decrypt(encryptedPassword);
    }

    private String getUUID(String vaultUsername, String vaultPassword) throws UserNotFoundException, PasswordMismatchException {
        int baddPasswordCount = 0;
        if (authenticator.get(vaultUsername) != null) {
            if (authenticator.get(vaultUsername).get(vaultPassword) != null) {
                return authenticator.get(vaultUsername).get(vaultPassword);
            } else {
                baddPasswordCount++;
                throw new PasswordMismatchException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    public boolean passwordCount(int pcount) {
        int counter = pcount;
        counter++;
        if (counter >= 3) {
            return true;
        } else {
            return false;
        }
    }
}