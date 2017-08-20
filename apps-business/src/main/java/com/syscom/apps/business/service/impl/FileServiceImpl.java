package com.syscom.apps.business.service.impl;

import java.io.File;
import java.io.IOException;

import javax.activation.DataHandler;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syscom.apps.business.service.FileService;

@Service("fileService")
@Transactional(rollbackFor=Exception.class)
public class FileServiceImpl implements FileService{
	
	@Value("${apps.base.path}")
	private String basePath;

	public void createFileResource(String path, DataHandler dataHandler) throws IOException {
		String absolutePath = basePath + path;
		File file = new File(absolutePath);
		FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(dataHandler.getInputStream()));

	}

	@Override
	public void createFileResource(DataHandler dataHandler) throws IOException {
		this.createFileResource(dataHandler.getName(), dataHandler);
	}
	

}
