package blackjack.domain

class Dealer(
    override val name: String,
    override val playerCards: Cards = Cards()
) : Participant(name = name, playerCards = playerCards) {

    val isBust = playerCards.score() > BLACK_JACK_SCORE

    fun isHit(): Boolean {
        return this.playerCards.score() <= SCORE_TO_REQUEST_A_CARD_FOR_DEALER
    }

    override fun addCard(card: Card) {
        this.playerCards.addCard(card)
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        private const val SCORE_TO_REQUEST_A_CARD_FOR_DEALER = 16
    }
}
