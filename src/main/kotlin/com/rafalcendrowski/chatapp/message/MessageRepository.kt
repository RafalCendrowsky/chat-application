package com.rafalcendrowski.chatapp.message

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : CrudRepository<Message, Long> {
    @Query("SELECT * FROM (" +
            "SELECT * FROM messages " +
            "ORDER BY sent DESC " +
            "LIMIT 10) " +
            "ORDER BY sent", nativeQuery = true)
    fun findLatest(): List<Message>

    @Query("SELECT * FROM ( " +
            "SELECT * FROM messages " +
            "WHERE sent > ( " +
            "SELECT sent FROM messages " +
            "WHERE message_id=:messageId) " +
            "ORDER BY sent DESC " +
            "LIMIT 10) " +
            "ORDER BY sent", nativeQuery = true)
    fun findLatestById(messageId: Long): List<Message>

}