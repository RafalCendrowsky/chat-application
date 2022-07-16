package com.rafalcendrowski.chatapp.common

import com.rafalcendrowski.chatapp.message.Message
import com.rafalcendrowski.chatapp.message.MessageVM
import com.rafalcendrowski.chatapp.user.User
import com.rafalcendrowski.chatapp.user.UserVM

class Mapper {
    companion object {
        fun mapToUserVM(user: User): UserVM {
            val userVM: UserVM = UserVM.DEFAULT_USER
            return userVM.apply {
                username = user.username
                messages = user.messages.map { mapToMessageVM(it, this) } as MutableList<MessageVM>
                userId = user.userId
                lastSeenId = user.lastSeenId
            }
        }

        fun mapToUser(userVM: UserVM): User {
            return User().apply {
                username = userVM.username
                userId = userVM.userId
                messages = userVM.messages.map { mapToMessage(it, this) } as MutableList<Message>
                lastSeenId = userVM.lastSeenId
            }
        }

        fun mapToMessageVM(message: Message): MessageVM {
            val user = when(message.user) {
                null -> UserVM.DEFAULT_USER
                else -> mapToUserVM(message.user!!)
            }
            return MessageVM(message.content, message.contentType, message.sent, message.messageId, user)
        }

        fun mapToMessage(messageVM: MessageVM): Message {
            return Message().apply {
                user = when (messageVM.user) {
                    null -> null
                    UserVM.DEFAULT_USER -> null
                    else -> mapToUser(messageVM.user!!)
                }
                content = messageVM.content
                contentType = messageVM.contentType
                sent = messageVM.sent
                messageId = messageVM.messageId
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
    }
}