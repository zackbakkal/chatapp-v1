package com.zack.projects.chatapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zack.projects.chatapp.model.Message;
import com.zack.projects.chatapp.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
		
	public MessageService(MessageRepository messageRepository) {
		super();
		this.messageRepository = messageRepository;
	}

	// Get messages sent by userName
	public List<Message> getMessagesSentBy(String userName) {
		
		List<Message> messages = this.messageRepository.findAll()
				.stream()
				.filter(message -> message.getSenderUserName().equals(userName))
				.collect(Collectors.toList());
		
		return messages;
		
	}
	
	// Get messages sent by userName to userName
	public List<Message> getMessagesSentByReceivedBy(String senderUserName, String receiverUserName)  {
		
		List<Message> messages = this.messageRepository.findAll()
				.stream()
				.filter(message -> message.getSenderUserName().equals(senderUserName)
						&& message.getReceiverUserName().equals(receiverUserName))
				.collect(Collectors.toList());
		
		return messages;
		
	}
	
	// Get messages received by userName
	public List<Message> getMessagesReceivedBy(String userName) {
		
		List<Message> messages = this.messageRepository.findAll()
				.stream()
				.filter(message -> message.getReceiverUserName().equals(userName))
				.collect(Collectors.toList());
		
		return messages;
		
	}
	
	// Get messages received by userName from userName
	public List<Message> getMessagesreceivedByfrom(String receiverUserName, String senderUserName)  {
		
		List<Message> messages = this.messageRepository.findAll()
				.stream()
				.filter(message -> message.getReceiverUserName().equals(receiverUserName)
						&& message.getSenderUserName().equals(senderUserName))
				.collect(Collectors.toList());
		
		return messages;
		
	}
		
	// Add message
	public Message addMessage(Message message) {
		
		message.setDateMessageSent();
		
		return this.messageRepository.save(message);
		
	}

}
