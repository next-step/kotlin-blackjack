package blackjack.domain

class Player(val name: String, var cards: List<Card> = emptyList()) {
    fun addCards(newCards: List<Card>) {
        cards = cards + newCards.map { it }
    }

    fun getCardList(): List<Card> = cards.toList()

    override fun toString(): String {
        val cardDescriptions = cards.joinToString(", ") { it.toString() }
        return "$name 카드: $cardDescriptions"
    }
}
