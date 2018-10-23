import vault.Vault;
import vault.VaultInterface;

public abstract class Main implements VaultInterface {

    public static void main(String[] args) {
        String aUserName = "michaelbaileym";
        String aPassword = "dwcgnzdt";

        Vault mikeVault = new Vault(null, null);

        try {
            mikeVault.newUser(aUserName, aPassword);
        } catch (vault.DuplicateUserException due) {
            System.out.println("Username is already in vault");
        } catch (vault.InvalidUsernameException iue) {
            System.out.println("Invalid Usernamennn");
        } catch (vault.InvalidPasswordException ipe) {
            System.out.println("Incorrect password");
        }

        try{
            String message = mikeVault.newSite("michaelbaileym", "dwcgnzdt", "amazon");
            System.out.println(message);
        } catch(vault.DuplicateSiteException dse){
            System.out.println("Site is already in vault");
        } catch(vault.UserNotFoundException unfe){
            System.out.println("Invalid mmusername");
        } catch(vault.UserLockedOutException ule){
            System.out.println("User is locked out");
        } catch (vault.PasswordMismatchException pme){
            System.out.println("Incorrect password");
        } catch (vault.InvalidSiteException ise){
            System.out.println("Invalid site name");
        }




    }
}