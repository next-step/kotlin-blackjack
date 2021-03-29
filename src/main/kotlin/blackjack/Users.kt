package blackjack

class Users(private val _users: List<User>) {
    val users: List<User>
        get() = _users.toList()

    fun firstDeal(cardExtractor: CardExtractor) {
        users.forEach { it.firstDeal(cardExtractor) }
    }
}
