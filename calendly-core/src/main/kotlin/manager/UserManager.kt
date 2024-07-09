package manager

import dal.UserDAL
import model.User
import org.springframework.stereotype.Component

@Component
class UserManager(private val userDAL: UserDAL) {
    fun getOrCreateUser(userId: String): User = userDAL.findOrCreateUser(userId)
}