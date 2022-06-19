package blackjack.domain

sealed class PlayerState(val score: Score) {
    abstract fun isFinished(): Boolean

    class Start(score: Score) : PlayerState(score) {
        override fun isFinished(): Boolean = false
    }

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
        fun of(score: Score, isRunning: Boolean = true): PlayerState {
            return when {
                score.isZero() -> Start(score)
                score.isBlackjack() -> Blackjack(score)
                score.isBust() -> Bust(score)
                isRunning -> Hit(score)
                else -> Stay(score)
            }
        }

        fun of(cards: PlayingCards, isRunning: Boolean = true): PlayerState {
            val score = Score.from(cards)

            return when {
                score.isZero() -> Start(score)
                score.isBlackjack() && cards.size == 2 -> Blackjack(score) // FIXME : Magic Number!
                score.isBust() -> Bust(score)
                isRunning -> Hit(score)
                else -> Stay(score)
            }
        }
    }
}
