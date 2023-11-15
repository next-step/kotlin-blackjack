package blackjack.domain

import blackjack.domain.Score.Companion.BLACK_JACK_SCORE

abstract class BlackJackPlayer(val name: String, val cards: Cards) {

    var status: PlayerStatus = PlayerStatus.HIT
        protected set

    init {
        validatePlayerStatus()
    }

    fun drawBy(card: Card) {
        cards.add(card)
        validatePlayerStatus()
    }

    fun isBurst(): Boolean {
        return status == PlayerStatus.BURST
    }

    fun isBlackJack(): Boolean {
        return cards.score() == Score(BLACK_JACK_SCORE)
    }

    fun match(other: BlackJackPlayer): WinLose {
        return when {
            isBlackJack() && other.isBlackJack() -> WinLose.DRAW
            isBlackJack() || other.isBurst() -> WinLose.WIN
            isBurst() || other.isBlackJack() -> WinLose.LOSE
            else -> cards.score().compareScore(other.cards.score())
        }
    }

    protected fun validatePlayerStatus() {
        when {
            cards.score().burst() -> forceUpdateStatus(PlayerStatus.BURST)
            cards.cards.size == 2 && (cards.score() == Score(BLACK_JACK_SCORE)) -> forceUpdateStatus(PlayerStatus.BLACKJACK)
            else -> forceUpdateStatus(PlayerStatus.HIT)
        }
    }

    protected fun forceUpdateStatus(status: PlayerStatus) {
        this.status = status
    }

    abstract fun firstOpenCards(): Cards

    abstract fun isHit(): Boolean
}
