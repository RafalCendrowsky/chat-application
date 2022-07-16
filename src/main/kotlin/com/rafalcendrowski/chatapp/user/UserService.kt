package com.rafalcendrowski.chatapp.user

interface UserService {

    fun persist(userVM: UserVM): UserVM

    fun findById(userId: Long): User?
}