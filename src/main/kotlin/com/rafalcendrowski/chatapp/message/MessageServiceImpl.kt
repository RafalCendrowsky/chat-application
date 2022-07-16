package com.rafalcendrowski.chatapp.message
import com.rafalcendrowski.chatapp.common.Mapper
import com.rafalcendrowski.chatapp.user.UserService
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(val messageRepository: MessageRepository, val userService: UserService) : MessageService {

    override fun persist(messageVM: MessageVM): MessageVM {
        val user = userService.findById(messageVM.user?.userId?:"")
        val message = findById(messageVM.messageId?:"") ?:
            Message(messageVM.content, messageVM.contentType, messageVM.sent, messageVM.messageId, user)
        messageRepository.save(message)
        return Mapper.mapToMessageVM(message)
    }

    override fun findById(id: String) : Message? = messageRepository.findById(id).orElse(null)

    override fun findLatest(lastSeenId: String): List<MessageVM> {
        return if (lastSeenId.isNotBlank()) {
            messageRepository.findLatestById(lastSeenId).map { Mapper.mapToMessageVM(it) }
        } else {
            messageRepository.findLatest().map { Mapper.mapToMessageVM(it) }
        }
    }
}