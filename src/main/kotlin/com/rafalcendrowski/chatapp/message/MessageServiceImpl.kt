package com.rafalcendrowski.chatapp.message
import com.rafalcendrowski.chatapp.common.Mapper
import com.rafalcendrowski.chatapp.user.UserService
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(val messageRepository: MessageRepository, val userService: UserService) : MessageService {

    override fun persist(messageVM: MessageVM): MessageVM {
        val user = userService.findById(messageVM.user?.userId?:-1L)
        val message = Message().apply {
            this.user = user
            content = messageVM.content
            contentType = messageVM.contentType
            sent = messageVM.sent
            messageId= messageVM.messageId
        }
        messageRepository.save(message)
        return Mapper.mapToMessageVM(message)
    }

    override fun findById(id: Long) : Message? = messageRepository.findById(id).orElse(null)

    override fun findLatest(lastSeenId: Long): List<MessageVM> {
        return if (lastSeenId != -1L) {
            messageRepository.findLatestById(lastSeenId).map { Mapper.mapToMessageVM(it) }
        } else {
            messageRepository.findLatest().map { Mapper.mapToMessageVM(it) }
        }
    }
}