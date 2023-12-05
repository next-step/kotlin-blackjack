package blackjack.domain

abstract class Participant(
    val name: String,
    val cards: Cards,
) {
    val score: Int
        get() = cards.calculateScore()

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return score > Game.BLACKJACK_SCORE
    }

    fun isBlackjack(): Boolean {
        return score == Game.BLACKJACK_SCORE
    }
}
