package blackjack.domain

class Player(val name: String, cards: List<String> = emptyList()) {
    var cards: List<String> = cards

    fun addCards(newCards: List<Card>) {
        cards = cards + newCards.map { it.toString() }
    }

    fun getCardList(): List<String> = cards
}
