package blackjack.domain.player.result

import blackjack.domain.card.Cards

enum class PlayerResult {
    NOT_FINISHED {
        override val opposite: PlayerResult
            get() = NOT_FINISHED
    },
    WIN {
        override val opposite: PlayerResult
            get() = LOSE
    },
    DRAW {
        override val opposite: PlayerResult
            get() = DRAW
    },
    LOSE {
        override val opposite: PlayerResult
            get() = WIN
    };

    abstract val opposite: PlayerResult

    companion object {
        fun of(playerCards: Cards, dealerCards: Cards): PlayerResult {
            val score = playerCards.score
            val otherScore = dealerCards.score

            return if (playerCards.isBust())
                LOSE
            else if (dealerCards.isBust())
                WIN
            else if (score > otherScore)
                WIN
            else if (score < otherScore)
                LOSE
            else if (playerCards.size < dealerCards.size)
                WIN
            else if (playerCards.size > dealerCards.size)
                LOSE
            else
                DRAW
        }
    }
}
