package blackjack.domain

class Player(val name: String, cards: List<Card> = emptyList()) {
    var cards: List<Card> = cards

    fun addCards(newCards: List<Card>) {
        cards = cards + newCards.map { it }
    }

    fun getCardList(): List<Card> = cards.toList()

    override fun toString(): String {
        val cardDescriptions = cards.joinToString(", ") { it.toString() }
        return "$name 카드: $cardDescriptions"
    }
}
