package com.rafalcendrowski.chatapp.message

import com.rafalcendrowski.chatapp.common.MESSAGES
import com.rafalcendrowski.chatapp.common.MESSAGES_LATEST
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping(MESSAGES)
class MessageController(val messageService: MessageService) {

    @GetMapping(MESSAGES_LATEST)
    fun getLatest(@RequestParam("messageId", defaultValue = "-1") messageId: Long): ResponseEntity<List<MessageVM>> {
        val messages = messageService.findLatest(messageId)
        return if (messages.isEmpty()) {
            with(ResponseEntity.noContent()) {
                header("messageId", messageId.toString())
                build<List<MessageVM>>()
            }
        } else {
            with(ResponseEntity.ok()) {
                header("messageId", messageId.toString())
                body(messages)
            }
        }

    }

    @PostMapping
    fun post(@RequestBody message: MessageVM) : ResponseEntity<MessageVM> {
        return with(ResponseEntity.ok()) {
            body(messageService.persist(message))
        }
    }
}