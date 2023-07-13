package blackjack.domain

class Player(
    val name: String,
    val cards: Cards = Cards(),
    betAmount: Double
) : Participant {
    val score
        get() = ScoreCalculator.calculateScore(cards)

    var betAmount: Double = betAmount
        private set

    init {
        require(name.isNotEmpty() && name.isNotBlank())
    }

    override fun receiveCards(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    override fun receiveCard(newCard: Card) {
        cards.add(newCard)
        if (score > BLACK_JACK) {
            loseAllMoney()
        }
    }

    fun plusMoney(amount: Double) {
        betAmount += amount
    }

    fun loseAllMoney() {
        betAmount = 0.0
    }

    companion object {
        const val DEFAULT_CARD_SIZE = 2
    }
}
