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

    fun match(other: BlackJackPlayer): WinLose {
        return cards.score().winLose(other.cards.score())
    }

    abstract fun firstOpenCards(): Cards

    abstract fun isHit(): Boolean
}
