package blackjack

class Users(private val _users: List<User>) {
    val users: List<User>
        get() = _users.toList()

    fun hit(cardExtractor: CardExtractor) {
        users.forEach { it.hit(cardExtractor) }
    }
}
