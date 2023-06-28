package blackjack.domain

data class Dealer(val cards: Cards = Cards()) {
    val name = "딜러"

    fun addCard(card: Card) {
        cards.addCard(card)
    }

    fun addCards(cards: List<Card>) {
        cards.map { addCard(it) }
    }

    fun score(): Int {
        return cards.score()
    }
}
