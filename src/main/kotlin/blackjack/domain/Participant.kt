package blackjack.domain

abstract class Participant(
    val name: String,
    val cards: Cards,
) {
    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return cards.calculateScore() > Game.BLACKJACK_SCORE
    }

    fun isBlackjack(): Boolean {
        return cards.calculateScore() == Game.BLACKJACK_SCORE
    }
}
