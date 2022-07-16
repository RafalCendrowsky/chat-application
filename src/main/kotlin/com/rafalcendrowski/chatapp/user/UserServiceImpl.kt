package com.rafalcendrowski.chatapp.user

import com.rafalcendrowski.chatapp.common.Mapper
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {

    override fun persist(userVM: UserVM): UserVM {
        val user = User().apply {
            username = userVM.username
            userId = userVM.userId
            lastSeenId = userVM.lastSeenId
        }
        userRepository.save(user)
        return Mapper.mapToUserVM(user)
    }

    override fun findById(userId: Long): User? = userRepository.findById(userId).orElse(null)
}