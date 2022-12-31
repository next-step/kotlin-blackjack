package blackjack.domain.player.result

import blackjack.domain.card.Cards

sealed interface BlackjackResult {
    val opposite: BlackjackResult
}

enum class PlayerResult(
    override val opposite: DealerResult
) : BlackjackResult {
    NOT_FINISHED(DealerResult.NOT_FINISHED),
    WIN(DealerResult.LOSE),
    DRAW(DealerResult.DRAW),
    LOSE(DealerResult.WIN);

    companion object {
        fun of(playerCards: Cards, dealerCards: Cards): PlayerResult {
            val score = playerCards.score
            val otherScore = dealerCards.score

            return when {
                playerCards.isBust() -> LOSE
                dealerCards.isBust() -> WIN
                score > otherScore -> WIN
                score < otherScore -> LOSE
                playerCards.size < dealerCards.size -> WIN
                playerCards.size > dealerCards.size -> LOSE
                else -> DRAW
            }
        }
    }
}

enum class DealerResult : BlackjackResult {
    NOT_FINISHED {
        override val opposite: PlayerResult
            get() = PlayerResult.NOT_FINISHED
    },
    WIN {
        override val opposite: PlayerResult
            get() = PlayerResult.LOSE
    },
    DRAW {
        override val opposite: PlayerResult
            get() = PlayerResult.DRAW
    },
    LOSE {
        override val opposite: PlayerResult
            get() = PlayerResult.WIN
    }
}
