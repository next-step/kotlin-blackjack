package blackjack.domain

class Player(val name: String, val cards: MutableList<String> = mutableListOf()) {
    fun addCards(newCards: List<Card>) {
        cards.addAll(newCards.map { it.toString() })
    }

    fun getCardList(): List<String> = cards
}
