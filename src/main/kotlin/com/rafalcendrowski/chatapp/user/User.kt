package com.rafalcendrowski.chatapp.user

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "user")
data class User(
        var username: String,
        @Id var userId: String? = null,
        var lastSeenId: String? = null)

data class UserVM(
        var username: String,
        var userId: String? = null,
        var lastSeenId: String? = null) {
    companion object {
        val DEFAULT_USER = UserVM("anonymous")
    }
}