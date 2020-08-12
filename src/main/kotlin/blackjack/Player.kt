package blackjack

class Player(val name: String) {
    private val cards = Cards()
    fun getCards() = cards
}
