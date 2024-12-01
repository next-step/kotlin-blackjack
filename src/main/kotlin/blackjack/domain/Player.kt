package blackjack.domain

class Player(val name: String, val cards: MutableList<String> = mutableListOf()) {
    fun addCards(newCards: List<String>) {
        cards.addAll(newCards)
    }

    fun getCardList(): List<String> = cards
}
