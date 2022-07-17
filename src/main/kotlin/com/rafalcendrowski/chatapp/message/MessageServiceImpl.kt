package com.rafalcendrowski.chatapp.message
import com.rafalcendrowski.chatapp.common.mapToMessage
import com.rafalcendrowski.chatapp.common.mapToVM
import com.rafalcendrowski.chatapp.user.UserService
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(val messageRepository: MessageRepository, val userService: UserService) : MessageService {

    override fun persist(messageVM: MessageVM): MessageVM {
        return messageRepository.save(messageVM.mapToMessage()).mapToVM()
    }

    override fun findById(id: Long) : Message? = messageRepository.findById(id).orElse(null)

    override fun findLatest(lastSeenId: Long): List<MessageVM> {
        return if (lastSeenId != -1L) {
            messageRepository.findLatestById(lastSeenId).map { it.mapToVM() }
        } else {
            messageRepository.findLatest().map { it.mapToVM() }
        }
    }
}