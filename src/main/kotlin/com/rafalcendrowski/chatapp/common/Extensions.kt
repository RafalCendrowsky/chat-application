package com.rafalcendrowski.chatapp.common

import com.rafalcendrowski.chatapp.message.Message
import com.rafalcendrowski.chatapp.message.MessageVM
import com.rafalcendrowski.chatapp.user.User
import com.rafalcendrowski.chatapp.user.UserVM

fun Message.mapToVM(): MessageVM {
    val user = when(this.user) {
        null -> UserVM.DEFAULT_USER
        else -> this.user!!.mapToUserVM()
    }
    return MessageVM(contentType.render(content), contentType, sent, messageId, user)
}

fun MessageVM.mapToMessage(): Message {
    val messageVM = this
    return Message().apply {
        user = when (messageVM.user) {
            null -> null
            UserVM.DEFAULT_USER -> null
            else -> messageVM.user!!.mapToUser()
        }
        content = messageVM.content
        contentType = messageVM.contentType
        sent = messageVM.sent
        messageId = messageVM.messageId
    }
}

fun User.mapToUserVM(): UserVM {
    val userVM: UserVM = UserVM.DEFAULT_USER
    val user = this
    return userVM.apply {
        username = user.username
        messages = user.messages.map { mapToMessageVM(it, this) } as MutableList<MessageVM>
        userId = user.userId
        lastSeenId = user.lastSeenId
    }
}

fun UserVM.mapToUser(): User {
    val userVM = this
    return User().apply {
        username = userVM.username
        userId = userVM.userId
        messages = userVM.messages.map { mapToMessage(it, this) } as MutableList<Message>
        lastSeenId = userVM.lastSeenId
    }
}

private fun mapToMessage(messageVM: MessageVM, user: User): Message {
    return Message().apply {
        this.user = user
        content = messageVM.content
        contentType = messageVM.contentType
        messageId = messageVM.messageId
        sent = messageVM.sent
    }
}

private fun mapToMessageVM(message: Message, userVM: UserVM): MessageVM {
    return MessageVM(message.content, message.contentType, message.sent, message.messageId, userVM)
}
