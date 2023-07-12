package blackjack.domain

class Player(
    val name: String,
    val cards: Cards = Cards(),
    betAmount: Int
) : Participant {
    val score
        get() = ScoreCalculator.calculateScore(cards)

    var betAmount: Int = betAmount
        private set

    init {
        require(name.isNotEmpty() && name.isNotBlank())
    }

    override fun receiveCards(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    override fun receiveCard(newCard: Card) {
        cards.add(newCard)
    }

    companion object {
        const val DEFAULT_CARD_SIZE = 2
    }
}
