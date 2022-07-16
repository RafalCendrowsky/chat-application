package com.rafalcendrowski.chatapp.user

interface UserService {

    fun persist(userVM: UserVM): UserVM

    fun save(user: User): User

    fun findById(userId: Long): User?
}