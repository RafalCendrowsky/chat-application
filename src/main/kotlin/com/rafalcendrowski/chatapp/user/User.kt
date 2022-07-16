package com.rafalcendrowski.chatapp.user

import com.rafalcendrowski.chatapp.message.Message
import com.rafalcendrowski.chatapp.message.MessageVM
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
open class User {
    open lateinit var username: String
    @OneToMany(mappedBy = "user")
    open lateinit var messages: MutableList<Message>
    @Id
    @GeneratedValue
    open var userId: Long? = null
    open var lastSeenId: String? = null
}

data class UserVM(
        var username: String,
        var messages: MutableList<MessageVM>,
        var userId: Long? = null,
        var lastSeenId: String? = null) {
    companion object {
        val DEFAULT_USER = UserVM("anonymous", arrayListOf())
    }
}