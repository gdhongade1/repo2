package com.myproj.service;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import com.myproj.dao.EmailFormatterRepository;
import com.myproj.pojo.Email;
import com.myproj.pojo.EmailFormatter;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.pop3.POP3Store;

public class TaskSchedularService {
	@Autowired
	EmailFormatterRepository dao;
	
	 public ArrayList<EmailFormatter> downloadEmailAttachments(String host, String port,
	            String userName, String password) {
	        Properties properties = new Properties();
	        ArrayList<EmailFormatter> emailList = new ArrayList<EmailFormatter>();
	       
	        // server setting
	        properties.put("mail.pop3.host", host);
	        properties.put("mail.pop3.port", port);
	 
	        // SSL setting
	        properties.setProperty("mail.pop3.socketFactory.class",
	                "javax.net.ssl.SSLSocketFactory");
	        properties.setProperty("mail.pop3.socketFactory.fallback", "false");
	        properties.setProperty("mail.pop3.socketFactory.port",
	                String.valueOf(port));
	 
	        Session session = Session.getDefaultInstance(properties);
	 
	        try {
	            // connects to the message store
	            Store store = session.getStore("pop3");
	            store.connect(userName, password);
	 
	            // opens the inbox folder
	            Folder folderInbox = store.getFolder("INBOX");
	            folderInbox.open(Folder.READ_ONLY);
	 
	            // fetches new messages from server
	            Message[] arrayMessages = folderInbox.getMessages();
	 
	            for (int i = 0; i < arrayMessages.length; i++) {
	                Message message = arrayMessages[i];
	                Address[] fromAddress = message.getFrom();
	                String from = fromAddress[0].toString();
	                String subject = message.getSubject();
	                String toList = parseAddresses(message.getRecipients(Message.RecipientType.TO));
	                String ccList = parseAddresses(message.getRecipients(Message.RecipientType.CC));
	                String bccList = parseAddresses(message.getRecipients(Message.RecipientType.BCC));
	                String sentDate = message.getSentDate().toString();
	 
	                String contentType = message.getContentType();
	                String messageContent = "";
	 
	                // store attachment file name, separated by comma
	                String attachFiles = "";
	 
	                if (contentType.contains("multipart")) {
	                    // content may contain attachments
	                    Multipart multiPart = (Multipart) message.getContent();
	                    MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	                    messageContent = getTextFromMimeMultipart(mimeMultipart);
	                    int numberOfParts = multiPart.getCount();
	                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
	                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
	                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
	                            // this part is attachment
	                            String fileName = part.getFileName();
	                            attachFiles += fileName + ", ";
	                            part.saveFile("E:/Attachment" + File.separator + fileName);
	                        } else {
	                            // this part may be the message content
	                            //messageContent = part.getContent().toString();
	                        	/*Object content = message.getContent();
	    	                    if (content != null) {
	    	                        messageContent = content.toString();
	    	                    }*/
	                        	 Object content = message.getContent();
	     	                    if (content != null) {
	     	                        messageContent = content.toString();
	     	                    }
	                        	messageContent = content.toString();
	                        }
	                    }
	 
	                    if (attachFiles.length() > 1) {
	                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
	                    }
	                } else if (contentType.contains("text/plain")
	                        || contentType.contains("text/html")) {
	                    Object content = message.getContent();
	                    if (content != null) {
	                        messageContent = content.toString();
	                    }
	                }
	 
	                // print out details of each message
	                System.out.println("Message #" + (i + 1) + ":");
	                EmailFormatter ef= new EmailFormatter();
	               ef.setActualDate(sentDate);
	                ef.setActualTime(sentDate);
	                ef.setAttachment("url");
	                ef.setBodyContent(messageContent);
	                ef.setBccEmail(bccList);
	                ef.setCcEmail(ccList);
	                ef.setFavourite(true);
	                ef.setFromEmail(from);
	                ef.setFromCustomer(from);
	                ef.setLabels("label1");
	                ef.setSubject(subject);
	                ef.setSubjectCustomer(subject);
	                ef.setTaskCompleted(true);
	                ef.setToEmail(toList);
	                ef.setUser_id(1L);
	                ef.setVisible(true);
	                
	                emailList.add(ef);
	                
	                System.out.println("\t From: " + from);
	                System.out.println("\t To : " + toList);
	                System.out.println("\t CC : " + ccList);
	                System.out.println("\t BCC : " + bccList);
	                System.out.println("\t Subject: " + subject);
	                System.out.println("\t Sent Date: " + sentDate);
	                System.out.println("\t Message: " + messageContent);
	                System.out.println("\t Attachments: " + attachFiles);
	            }
	 
	            // disconnect
	            folderInbox.close(false);
	            store.close();
	           
	        } catch (NoSuchProviderException ex) {
	            System.out.println("No provider for pop3.");
	            ex.printStackTrace();
	        } catch (MessagingException ex) {
	            System.out.println("Could not connect to the message store");
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return emailList;
	    }
	
	 public Email checkEmail(String host, String storeType, String user, String password) {
		 Email e= new Email();
		try {
			//create properties field
			Properties properties = new Properties();
			properties.put("mail.store.protocol", "pop3");
		    properties.put("mail.pop3.host", host);
		    properties.put("mail.pop3.port", "995");
		    properties.put("mail.pop3.starttls.enable", "true");
		    Session emailSession = Session.getDefaultInstance(properties);
		    
		  //create the POP3 store object and connect with the pop server
		    Store store = emailSession.getStore("pop3s");
		    store.connect(host, user, password);
		    
		    //create the folder object and open it
		      Folder emailFolder = store.getFolder("INBOX");
		      emailFolder.open(Folder.READ_WRITE);
		      
		    BufferedReader reader = new BufferedReader(new InputStreamReader(
		    	      System.in));
		      
		   // retrieve the messages from the folder in an array and print it
		     Message[] messages = emailFolder.getMessages();
		     System.out.println("messages.length---" + messages.length);
		     
		     for (int i = 0; i < 1; i++) {
		    	 String body=null;
		         Message message = messages[i];
		         Object content = message.getContent();  
		         if (content instanceof String){  
		             body = (String)content;  
		         }else if (content instanceof Multipart){  
		             Multipart mp = (Multipart)content;  
		             for (int j = 0; j < mp.getCount(); j++) {  
			             BodyPart bodyPart = mp.getBodyPart(j);  
			             InputStream stream = bodyPart.getInputStream();  
			             BufferedReader br = new BufferedReader(new InputStreamReader(stream));  
			              while (br.ready()) {  
			               System.out.println(br.readLine());  
			              }  
			             System.out.println();  
			         
			         }
		         } 
		         Address to[] =  message.getRecipients(Message.RecipientType.TO);
		         Address cc[] =  message.getRecipients(Message.RecipientType.CC);
		         Address bcc[] =  message.getRecipients(Message.RecipientType.BCC);
		         
		         e.setTo(to);
		         e.setCc(cc);
		         e.setBcc(bcc);
		         
		         e.setDate(message.getReceivedDate());
		         e.setFrom( message.getFrom());
		         e.setSubject(message.getSubject());
		        
		         System.out.println("---------------------------------");
		         System.out.println("Email Number " + (i + 1));
		         System.out.println("Subject: " + message.getSubject());
		         System.out.println("From: " + message.getFrom()[0]);
		         System.out.println("To: " );
		         Arrays.asList(to).forEach(System.out::println);
		         System.out.println("CC: " );
		         Arrays.asList(cc).forEach(System.out::println);
		         System.out.println("BCC: " );
		         Arrays.asList(bcc).forEach(System.out::println);
		         
		         System.out.println("Date : " + message.getReceivedDate());
		         System.out.println("Body: \n"+ body);

		     

		      }
		   
		     
		     //close the store and folder objects
		      emailFolder.close(false);
		      store.close();
		      
		} catch (NoSuchProviderException e1) {
			e1.printStackTrace();
		} catch (MessagingException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
					}
		return e;

	 }
	 
	 
	 private String getTextFromMimeMultipart(
		        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
		    String result = "";
		    int count = mimeMultipart.getCount();
		    for (int i = 0; i < count; i++) {
		        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
		        if (bodyPart.isMimeType("text/plain")) {
		            result = result + "\n" + bodyPart.getContent();
		            break; // without break same text appears twice in my tests
		        } else if (bodyPart.isMimeType("text/html")) {
		            String html = (String) bodyPart.getContent();
		            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
		        } else if (bodyPart.getContent() instanceof MimeMultipart){
		            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
		        }
		    }
		    return result;
		}
	 
	 private String parseAddresses(Address[] address) {
	        String listAddress = "";
	 
	        if (address != null) {
	            for (int i = 0; i < address.length; i++) {
	                listAddress += address[i].toString() + ", ";
	            }
	        }
	        if (listAddress.length() > 1) {
	            listAddress = listAddress.substring(0, listAddress.length() - 2);
	        }
	 
	        return listAddress;
	    }
	 
}
