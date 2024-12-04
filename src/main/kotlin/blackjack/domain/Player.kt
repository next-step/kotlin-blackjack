package blackjack.domain

class Player(val name: String, val cards: MutableList<String> = mutableListOf()) {
    fun addCards(newCards: List<Card>) {
        cards.addAll(newCards.map { "${it.number} of ${it.shape}" })
    }

    fun getCardList(): List<String> = cards
}
