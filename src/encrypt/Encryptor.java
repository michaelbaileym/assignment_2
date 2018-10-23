package encrypt;

import vault.VaultInterface;

public interface Encryptor {
	String encrypt(String s);
	String decrypt(String s);
}


