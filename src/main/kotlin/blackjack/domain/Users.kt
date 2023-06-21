package blackjack.domain

data class Users(private val userList: Set<User>) : Iterable<User> {
    val size = userList.size

    init {
        require(userList.isNotEmpty()) { EMPTY_USER_LIST_ERROR_MESSAGE }
    }

    constructor(nameList: List<String>) : this(nameList.map { User(it) }.toSet()) {
        require(nameList.size == userList.size) { DUPLICATE_USER_ERROR_MESSAGE }
    }

    override fun iterator(): Iterator<User> {
        return userList.iterator()
    }

    companion object {
        private const val EMPTY_USER_LIST_ERROR_MESSAGE = "1명 이상의 유저가 필요합니다"
        private const val DUPLICATE_USER_ERROR_MESSAGE = "중복된 유저가 존재합니다"
    }
}
