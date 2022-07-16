package com.rafalcendrowski.chatapp.message

interface MessageService {

    fun persist(messageVM: MessageVM): MessageVM

    fun findById(id: String): Message?

    fun findLatest(lastSeenId: String = ""): List<MessageVM>
}