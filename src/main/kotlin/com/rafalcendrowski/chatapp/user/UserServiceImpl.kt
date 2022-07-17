package com.rafalcendrowski.chatapp.user

import com.rafalcendrowski.chatapp.common.mapToUser
import com.rafalcendrowski.chatapp.common.mapToUserVM
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {

    override fun persist(userVM: UserVM): UserVM {
        return userRepository.save(userVM.mapToUser()).mapToUserVM()
    }

    override fun save(user: User): User = userRepository.save(user)

    override fun findById(userId: Long): User? = userRepository.findById(userId).orElse(null)
}