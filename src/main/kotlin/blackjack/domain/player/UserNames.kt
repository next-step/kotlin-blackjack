package blackjack.domain.player

class UserNames(names: List<String>) {
    val userNames: List<UserName>

    init {
        validateDuplicatedName(names)
        userNames = names.map { UserName(it) }
    }

    private fun validateDuplicatedName(names: List<String>) {
        val toSet = names.toSet()
        require(names.size == toSet.size) { "플레이어끼리 이름이 중복될 수 없습니다." }
    }

    fun <T> map(transform: (UserName) -> T) = userNames.map(transform)

    fun <T> zip(other: Iterable<T>): List<Pair<UserName, T>> = userNames.zip(other)
}