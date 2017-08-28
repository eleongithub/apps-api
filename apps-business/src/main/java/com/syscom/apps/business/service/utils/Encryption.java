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
	 * Convertir des données de la Base64 vers un tableau de byte.
	 * 
	 * @param data : données à convertir {@link String}
	 * @return un tableau de byte.
	 */
	private byte[] base64ToByte(String data) {
		Base64 decoder = new Base64();
		return decoder.decode(data);

	}

	/**
	 * Convertir des données de type byte en Base64.
	 * 
	 * @param data : tableau de byte à convertir en Base64.
	 * @return {@link String}
	 */
	private String byteToBase64(byte[] data) {
		Base64 encoder = new Base64();
		return encoder.encodeToString(data);
	}

	/**
	 * Decrypter un mot de passe avec un sel de contrôle.
	 * 
	 * @param password : mot de passe
	 * @param salt : sel de contrôle
	 * @return {@link String}
	 */
	public String decrypt(String password, String salt)
			throws NoSuchAlgorithmException {
		byte[] bSalt = base64ToByte(salt);

		// convert the String password to Byte type with the salt
		byte[] digest = getHash(password, bSalt);

		// Obtain the fingerprint of the password
		return byteToBase64(digest);
	}

	/**
	 * Encrypter un mot de passe clair pour fournir un mot de passe haché avec son sel de contrôle.
	 * 
	 * @param password : mot de passe à encrypter {@link String}
	 * @return 
	 * @throws NoSuchAlgorithmException
	 */
	public Credentials encrypt(String password) throws NoSuchAlgorithmException {
		// Use a secure Random
		SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM);

		// Génerate a salt 64 byte (8 byte)
		byte[] bSalt = new byte[8];
		secureRandom.nextBytes(bSalt);

		// Digest computation using a number of hachage
		byte[] bDigest = getHash(password, bSalt);

		// Convert the byte type to Base64
		String passwordEncrypt = byteToBase64(bDigest);

		// Convert the byte type to Base64
		String sSalt = byteToBase64(bSalt);
		
		// Return sDigest and sSalt which are stored in the data base
		return new Credentials(passwordEncrypt, sSalt);


	}

	/**
	 *  Créer un token pour une réinitialisation de mot de passe.
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
	 * Méthode pour hacher un mot de passe clair avec un sel de contrôle. Hachage effectué un nombre de fois important.
	 * @param : password mot de passe clair.
	 * @param salt : sel de contrôle.
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private byte[] getHash(String password, byte[] salt)
			throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		for (int i = 0; i < NUMBER_HACHAGE_ITERATION; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;

	}
}
