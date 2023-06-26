package blackjack.domain

data class Users(private val userList: Set<User>) : Iterable<User> by userList {

    init {
        require(userList.isNotEmpty()) { EMPTY_USER_LIST_ERROR_MESSAGE }
    }

    companion object {
        private const val EMPTY_USER_LIST_ERROR_MESSAGE = "1명 이상의 유저가 필요합니다"
    }
}
