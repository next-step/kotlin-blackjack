package blackjack.domain

import blackjack.domain.Score.Companion.BLACK_JACK_SCORE

abstract class BlackJackPlayer(
    val name: String,
    val cards: Cards
) {

    var status: PlayerStatus = PlayerStatus.HIT
        protected set

    fun drawBy(card: Card) {
        cards.add(card)
    }

    fun isBurst(): Boolean {
        return cards.score().burst()
    }

    fun isBlackJack(): Boolean {
        return cards.score() == Score(BLACK_JACK_SCORE)
    }

    fun blackjack() {
        status = PlayerStatus.BLACKJACK
    }

    fun burst() {
        status = PlayerStatus.BURST
    }

    fun match(other: BlackJackPlayer): WinLose {
        return cards.score().winLose(other.cards.score())
    }

    abstract fun firstOpenCards(): Cards

    abstract fun isHit(): Boolean
}
