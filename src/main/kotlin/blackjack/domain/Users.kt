package blackjack.domain

/**
 * 클래스에 대한 설명을 적어주세요.
 * Created by Jaesungchi on 2022.06.07..
 */
class Users(val users: List<User>) {

    fun getSize() = users.size

    fun getUsersName() = users.joinToString(", ") { it.name }

    companion object {
        fun of(usersName: String): Users {
            return Users(usersName.split(",").map { User(it) })
        }
    }
}
