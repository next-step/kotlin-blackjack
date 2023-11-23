package blackjack.domain

abstract class Participant {
    abstract val name: String
    abstract val cards: Cards

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return cards.calculateScore() >= Game.BLACKJACK_SCORE
    }
}
