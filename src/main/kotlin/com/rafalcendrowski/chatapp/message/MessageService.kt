package com.rafalcendrowski.chatapp.message

interface MessageService {

    fun persist(messageVM: MessageVM): MessageVM

    fun findById(id: Long): Message?

    fun findLatest(lastSeenId: Long = -1L): List<MessageVM>
}