package blackjack.domain

import blackjack.domain.Score.Companion.BLACK_JACK_SCORE

abstract class BlackJackPlayer {

    abstract val name: String
    abstract val cards: Cards

    var status: PlayerStatus = PlayerStatus.HIT
        protected set

    fun drawBy(card: Card) {
        cards.add(card)
        if (cards.score() > Score(BLACK_JACK_SCORE)) {
            burst()
        }
    }

    fun isBurst(): Boolean {
        return status == PlayerStatus.BURST
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
        return when {
            isBlackJack() && other.isBlackJack() -> WinLose.DRAW
            isBlackJack() || other.isBurst() -> WinLose.WIN
            isBurst() || other.isBlackJack() -> WinLose.LOSE
            else -> cards.score().compareScore(other.cards.score())
        }
    }

    abstract fun firstOpenCards(): Cards

    abstract fun isHit(): Boolean
}
