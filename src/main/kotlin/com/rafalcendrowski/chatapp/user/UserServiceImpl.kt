package com.rafalcendrowski.chatapp.user

import com.rafalcendrowski.chatapp.common.Mapper
import com.rafalcendrowski.chatapp.message.Message
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val userRepository: UserRepository) : UserService {

    override fun persist(userVM: UserVM): UserVM {
        val user = Mapper.mapToUser(userVM)
        userRepository.save(user)
        return Mapper.mapToUserVM(user)
    }

    override fun save(user: User): User = userRepository.save(user)

    override fun findById(userId: Long): User? = userRepository.findById(userId).orElse(null)
}