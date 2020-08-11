package blackjack

class Player(val name: String) {
    private val cards = listOf("2♥", "8♠")
    fun getCards() = cards
}
