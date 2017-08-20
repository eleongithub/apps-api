package com.syscom.apps.business.service;

import java.io.IOException;

import javax.activation.DataHandler;

/**
 * 
 * @author Eric LEGBA
 *
 */
public interface FileService {

	/**
	 * Enregistrer un fichier sur le fileSystem à partir d'un dataHandler {@link DataHandler}
	 * 
	 * @param dataHandler
	 * @return
	 * @throws IOException
	 */
	void createFileResource(DataHandler dataHandler) throws IOException;
	
	/**
	 * Enregistrer un fichier sur le fileSystem à partir d'un dataHandler {@link DataHandler} et du nom du fichier
	 * @param fileName
	 * @param dataHandler
	 * @return
	 * @throws IOException
	 */
	void createFileResource(String fileName, DataHandler dataHandler) throws IOException;
}
