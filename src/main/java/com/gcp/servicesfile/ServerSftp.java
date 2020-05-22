package com.gcp.servicesfile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession; 

public class ServerSftp {
    
	@Value("${sftp.privateKey:#{null}}")
    private Resource sftpPrivateKey;


	
	private DefaultSftpSessionFactory gimmeFactory(){
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
        factory.setHost("34.71.222.224");
        factory.setPort(22);
        factory.setPrivateKey(sftpPrivateKey);
        factory.setPrivateKeyPassphrase("falabella");
        factory.setAllowUnknownKeys(true);
        factory.setUser("userfala");
        factory.setPassword("falabella");
        return factory;
    }

    public void upload(String filename) throws FileNotFoundException{
    	

        SftpSession session = gimmeFactory().getSession();       
        
        File initialFile = new File(filename);
        InputStream targetStream = new FileInputStream(initialFile);
      
        
        try {
            session.write(targetStream, "/userfala/files/mynewfile.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        session.close();
    }

    public String download(){
        SftpSession session = gimmeFactory().getSession();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            session.read("upload/downloadme.txt", outputStream);
            return new String(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
