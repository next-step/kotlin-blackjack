package blackjack.domain

abstract class BlackJackPlayer(
    val name: String,
    val cards: Cards
) {

    fun drawBy(trumpCard: TrumpCard) {
        cards.add(trumpCard.draw())
    }

    fun isBurst(): Boolean {
        return cards.score().burst()
    }

    abstract fun isHit(): Boolean
}
