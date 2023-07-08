package blackjack.domain

class Player(
    val name: String,
    cards: Cards = Cards()
) : Participant {
    var cards: Cards = cards
        private set
    val score
        get() = ScoreCalculator.calculateScore(cards)

    init {
        require(name.isNotEmpty() && name.isNotBlank())
    }

    override fun receiveCards(newCards: List<Card>) {
        cards = Cards(cards.values + newCards)
    }

    override fun receiveCard(newCard: Card) {
        cards = Cards(cards.values + newCard)
    }

    companion object {
        const val DEFAULT_CARD_SIZE = 2
    }
}
