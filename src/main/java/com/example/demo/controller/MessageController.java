package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    // GET all messages (admin panel)
    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // GET inbox for a student
    @GetMapping("/inbox/{userId}")
    public List<Message> getInbox(@PathVariable Long userId) {
        return messageRepository.findByToUserId(userId);
    }

    // GET sent messages for a teacher
    @GetMapping("/sent/{userId}")
    public List<Message> getSent(@PathVariable Long userId) {
        return messageRepository.findByFromUserId(userId);
    }

    // POST — teacher sends a message to student
    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        try {
            message.setIsRead(false);
            return ResponseEntity.ok(messageRepository.save(message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending message: " + e.getMessage());
        }
    }

    // DELETE — admin deletes a message
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        if (!messageRepository.existsById(id)) return ResponseEntity.notFound().build();
        messageRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
