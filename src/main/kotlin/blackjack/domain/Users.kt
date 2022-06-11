package blackjack.domain

/**
 * 유저들을 저장하는 일급 컬렉션
 * Created by Jaesungchi on 2022.06.07..
 */
class Users(val users: List<User>) {

    companion object {
        fun of(usersName: String): Users {
            return Users(usersName.split(",").map { User(it) })
        }
    }
}
