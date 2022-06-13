package blackjack.domain

class Dealer(
    override val name: String,
    override val playerCards: Cards = Cards()
) : Participant(name, playerCards) {

    override fun isBust() = playerCards.score() > BLACK_JACK_SCORE

    override fun addCard(card: Card) {
        this.playerCards.addCard(card)
    }

    override fun isDealer(): Boolean {
        return true
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
    }
}
