package step2.blackjack.model

/**
 * 유저 리스트
 * */
data class UserList(val userList: List<User>) {

    init {
        require(userList.size in MIN_SIZE..MAX_SIZE) {
            "유저 리스트의 수는 ${MIN_SIZE}부터 ${MAX_SIZE}까지 여야합니다."
        }
    }

    companion object {

        private const val DELIMITER = ","
        private const val MIN_SIZE = 1
        private const val MAX_SIZE = 10

        /**
         * 유저 리스트 생성
         * */
        fun from(userListText: String): UserList {
            require(userListText.isNotBlank()) {
                "유저 이름의 리스트는 비어 있지 않아야 한다."
            }

            val userList = userListText.split(DELIMITER).map { name -> User.from(name) }
            return UserList(userList)
        }
    }
}
