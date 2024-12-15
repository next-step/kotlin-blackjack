package blackjack.card

data class DefaultCardHolder(
    var cards: Cards = Cards(),
) {
    fun take(newCard: Card) {
        cards.add(newCard)
    }

    fun take(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    fun score(): Int {
        return cards.bestScore()
    }
}
