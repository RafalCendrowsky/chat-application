package com.rafalcendrowski.chatapp.common

import com.rafalcendrowski.chatapp.message.Message
import com.rafalcendrowski.chatapp.message.MessageVM
import com.rafalcendrowski.chatapp.user.User
import com.rafalcendrowski.chatapp.user.UserVM

class Mapper {
    companion object {
        fun mapToUserVM(user: User): UserVM {
            return UserVM(user.username, user.userId, user.lastSeenId)
        }

        fun mapToMessageVM(message: Message): MessageVM {
            val user = when(message.user) {
                null -> UserVM.DEFAULT_USER
                else -> mapToUserVM(message.user!!)
            }
            return MessageVM(message.content, message.contentType, message.sent, message.messageId, user)
        }
    }
}