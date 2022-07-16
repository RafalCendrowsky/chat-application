package com.rafalcendrowski.chatapp.message

import com.rafalcendrowski.chatapp.user.User
import com.rafalcendrowski.chatapp.user.UserVM
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table(name = "message")
data class Message(
        val content: String,
        val contentType: ContentType,
        val sent: Instant,
        @Id var messageId: String? = null,
        var user: User? = null)

data class MessageVM(
        val content: String,
        val contentType: ContentType,
        val sent: Instant,
        var messageId: String? = null,
        var user: UserVM? = null)

enum class ContentType {
    PLAIN
}