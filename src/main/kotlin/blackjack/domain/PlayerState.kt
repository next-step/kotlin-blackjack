package blackjack.domain

sealed class PlayerState(val score: Score) {
    abstract fun isFinished(): Boolean

    class Hit(score: Score) : PlayerState(score) {
        override fun isFinished(): Boolean = false
    }

    class Stay(score: Score) : PlayerState(score) {
        override fun isFinished(): Boolean = true
    }

    class Bust(score: Score) : PlayerState(score) {
        override fun isFinished(): Boolean = true
    }

    class Blackjack(score: Score) : PlayerState(score) {
        override fun isFinished(): Boolean = true
    }

    companion object {
        fun of(cards: PlayingCards, isRunning: Boolean = true): PlayerState {
            val score = Score.from(cards)

            return when {
                score.isBlackjack() && cards.size == START_CARD_COUNT -> Blackjack(score) // FIXME : Magic Number!
                score.isBust() -> Bust(score)
                isRunning -> Hit(score)
                else -> Stay(score)
            }
        }
    }
}
