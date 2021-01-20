package com.zack.projects.chatapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zack.projects.chatapp.model.Message;
import com.zack.projects.chatapp.service.MessageService;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;

	// Get messages sent by userName
	@GetMapping("sentby/{userName}")
	public List<Message> getMessagesSentBy(
			@PathVariable(name = "userName") String userName) {
		
		List<Message> messages = this.messageService.getMessagesSentBy(userName);
		
		return messages;
		
	}
	
	// Get messages sent by userName to userName
	@GetMapping("sentby/{senderUserName}/to/{receiverUserName}")
	public List<Message> getMessagesSentByReceivedBy(
			@PathVariable(name = "senderUserName") String senderUserName,
			@PathVariable(name = "receiverUserName") String receiverUserName)  {
		
		List<Message> messages = this.messageService.getMessagesSentByReceivedBy(senderUserName, receiverUserName);
		
		return messages;
		
	}
	
	// Get messages received by userName
	@GetMapping("receivedby/{userName}")
	public List<Message> getMessagesReceivedBy(
			@PathVariable(name = "userName") String userName) {
		
		List<Message> messages = this.messageService.getMessagesReceivedBy(userName);
		
		return messages;
		
	}
	
	// Get messages received by userName from userName
	@GetMapping("receivedby/{receiverUserName}/from/{senderUserName}")
	public List<Message> getMessagesreceivedByfrom(
			@PathVariable(name = "receiverUserName") String receiverUserName,
			@PathVariable(name = "senderUserName") String senderUserName)  {
		
		List<Message> messages = this.messageService.getMessagesreceivedByfrom(receiverUserName, senderUserName);
		
		return messages;
		
	}
		
	// Add message
	@PostMapping
	public ResponseEntity<Message> addMessage(@RequestBody Message message) {
		
		message.setDateMessageSent();
		
		return ResponseEntity.ok().body(this.messageService.addMessage(message));
		
	}

}
