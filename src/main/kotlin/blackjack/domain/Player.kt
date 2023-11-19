package blackjack.domain

class Player(
    val name: String,
) {
    val cards: Cards = Cards()

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun isFinished(): Boolean {
        return cards.calculateScore(false) >= BLACKJACK_SCORE
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
