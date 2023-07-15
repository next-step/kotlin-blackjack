package blackjack.domain

class Player(
    val name: String,
    val cards: Cards = Cards(),
    val betAmount: Double
) : Participant {
    var profit: Double = INIT_PROFIT
        private set
    val score
        get() = ScoreCalculator.calculateScore(cards)

    init {
        require(name.isNotEmpty() && name.isNotBlank())
    }

    override fun receiveCards(newCards: List<Card>) {
        cards.addAll(newCards)
    }

    override fun receiveCard(newCard: Card) {
        cards.add(newCard)
        if (score > BLACK_JACK) {
            loseBetAmount()
        }
    }

    fun plusMoney(amount: Double) {
        profit += amount
    }

    fun loseBetAmount() {
        profit = -betAmount
    }

    companion object {
        const val DEFAULT_CARD_SIZE = 2
        const val INIT_PROFIT = 0.0
    }
}
