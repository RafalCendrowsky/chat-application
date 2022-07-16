package com.rafalcendrowski.chatapp.message

import com.rafalcendrowski.chatapp.user.User
import com.rafalcendrowski.chatapp.user.UserVM
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "messages")
open class Message() {
    open lateinit var content: String
    open lateinit var contentType: ContentType
    open lateinit var sent: Instant
    @Id
    @GeneratedValue
    open var messageId: Long? = null
    @ManyToOne
    open var user: User? = null

    constructor(content: String,
                contentType: ContentType,
                sent: Instant,
                messageId: Long? = null,
                user: User? = null) : this() {
        this.content = content
        this.contentType = contentType
        this.sent = sent
        this.messageId = messageId
        this.user = user
    }
}

data class MessageVM(
        val content: String,
        val contentType: ContentType,
        val sent: Instant,
        var messageId: Long? = null,
        var user: UserVM? = null)

enum class ContentType {
    PLAIN
}