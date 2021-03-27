package blackjack

class Users(private val _users: List<User>) {
    val users: List<User>
        get() = _users.toList()

    fun hit(cardExtractor: CardExtractor) {
        repeat(HIT_COUNT) {
            users.forEach { it.addCard(cardExtractor.getCard()) }
        }
    }

    companion object {
        private const val HIT_COUNT = 2
    }
}
