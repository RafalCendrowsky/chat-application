package com.rafalcendrowski.chatapp.message

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : CrudRepository<Message, String> {
    @Query("SELECT * FROM (" +
            "SELECT * FROM message " +
            "ORDER BY sent DESC " +
            "LIMIT 10) " +
            "ORDER BY sent")
    fun findLatest(): List<Message>

    @Query("SELECT * FROM ( " +
            "SELECT * FROM message " +
            "WHERE sent > ( " +
            "SELECT sent FROM message " +
            "WHERE message_id=:messageId) " +
            "ORDER BY sent DESC " +
            "LIMIT 10) " +
            "ORDER BY sent")
    fun findLatestById(messageId: String): List<Message>

}