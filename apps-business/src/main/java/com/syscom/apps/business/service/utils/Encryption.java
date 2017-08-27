package com.syscom.apps.business.service.utils;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

/**
 * Utilitaire pour encrypter/decrypter les credentials (mot de passe, sel de contrôle)
 * 
 * @author Eric LEGBA
 *
 */
public class Encryption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String ALGORITHM = "SHA-512";

	private static final int NUMBER_HACHAGE_ITERATION = 100;

	private static final String SECURE_RANDOM = "SHA1PRNG";

	private static Encryption instance;

	/**
	 * Method returns unique instance of the class (Singleton).
	 *
	 * @return {@link Encryption}.
	 */
	public static Encryption getInstance() {
		if (null == instance) {
			instance = new Encryption();
		}
		return instance;
	}

	/**
	 * Redefined constructor as private to force to call getInstance() méthod
	 * 
	 * {@link #getInstance()}.
	 */
	private Encryption() {

	}

	/**
	 * 
	 * From a base 64 representation, returns the corresponding byte[]
	 * 
	 * @param data {@link String} data The base64 representation
	 * @return byte[]
	 * @author Eric LEGBA
	 */
	public byte[] base64ToByte(String data) {
		Base64 decoder = new Base64();
		return decoder.decode(data);

	}

	/**
	 * 
	 * From a byte[] returns a base 64 representation
	 * 
	 * @param data
	 *            byte[]
	 * @return {@link String}
	 * @author Eric LEGBA
	 */
	public String byteToBase64(byte[] data) {
		Base64 encoder = new Base64();
		return encoder.encodeToString(data);
	}

	/**
	 * 
	 * @param password
	 * @param salt
	 * @return {@link String}
	 * @author Eric LEGBA
	 */
	public String decrypt(String password, String salt)
			throws NoSuchAlgorithmException {
		byte[] bSalt = base64ToByte(salt);

		// convert the String password to Byte type with the salt
		byte[] digest = getHash(NUMBER_HACHAGE_ITERATION, password, bSalt);

		// Obtain the fingerprint of the password
		return byteToBase64(digest);
	}

	/**
	 * 
	 * @param {@link String} password
	 * @return {@link Credentials} credentials
	 * @throws NoSuchAlgorithmException
	 * @author Eric LEGBA
	 */
	public Credentials encrypt(String password) throws NoSuchAlgorithmException {
		// Use a secure Random
		SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM);

		// Génerate a salt 64 byte (8 byte)
		byte[] bSalt = new byte[8];
		secureRandom.nextBytes(bSalt);

		// Digest computation using a number of hachage
		byte[] bDigest = getHash(NUMBER_HACHAGE_ITERATION, password, bSalt);

		// Convert the byte type to Base64
		String passwordEncrypt = byteToBase64(bDigest);

		// Convert the byte type to Base64
		String sSalt = byteToBase64(bSalt);
		
		// Return sDigest and sSalt which are stored in the data base
		return new Credentials(passwordEncrypt, sSalt);


	}

	/**
	 * 
	 * @return {@link String}
	 * @throws NoSuchAlgorithmException
	 */
	public String createTokenReinitMDP() throws NoSuchAlgorithmException {
		// Use a secure Random
		SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM);

		// Génerate a salt 64 byte (8 byte)
		byte[] bSalt = new byte[8];
		secureRandom.nextBytes(bSalt);

		// Convert the byte type to Base64
		return byteToBase64(bSalt);

	}

	/**
	 * 
	 * Method to hash a password with salt by algorithm
	 * 
	 * @param {@link Integer} iterationNb number of iteration
	 * @param {@link String} password the initial password which will be hash
	 * @param byte[] salt the salt
	 * @return byte[]
	 * @author Eric LEGBA
	 */
	private byte[] getHash(int iterationNb, String password, byte[] salt)
			throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		for (int i = 0; i < iterationNb; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;

	}
}
