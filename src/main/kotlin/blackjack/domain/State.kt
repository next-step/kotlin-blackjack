package blackjack.domain

import java.math.BigDecimal

sealed class State(
    val cards: Cards,
) {
    abstract fun hit(card: Card): State

    abstract fun stay(): State

    abstract fun gameResult(other: State): GameResult

    abstract fun profitMultiple(): BigDecimal

    fun calculateScore(): Score = cards.calculateScore()

    companion object {
        fun of(cards: Cards): State {
            if (cards.calculateScore().isBlackJack()) {
                return BlackJack(cards)
            }

            return Running(cards)
        }
    }
}

class Running(
    cards: Cards,
) : State(cards) {
    override fun hit(card: Card): State {
        val addedCards = cards + card
        val score = addedCards.calculateScore()
        if (score.isBlackJack()) {
            return BlackJack(addedCards)
        }

        if (score.isBurst()) {
            return Burst(addedCards)
        }

        return Running(addedCards)
    }

    override fun stay(): State = Stay(cards)

    override fun gameResult(other: State): GameResult {
        throw IllegalStateException("끝난 상태에서만 승부를 낼 수 있습니다.")
    }

    override fun profitMultiple(): BigDecimal {
        throw IllegalStateException("끝난 상태에서만 수익 배수를 반환할 수 있습니다.")
    }
}

sealed class Finished(
    cards: Cards,
) : State(cards) {
    override fun hit(card: Card): State {
        throw IllegalStateException("끝난 상태에서 hit 할 수 없습니다.")
    }

    override fun stay(): State {
        throw IllegalStateException("끝난 상태에서 stay 할 수 없습니다.")
    }

    override fun gameResult(other: State): GameResult {
        if (this is Burst) {
            return GameResult.LOSE
        }

        if (other is Burst) {
            return GameResult.WIN
        }

        val score = calculateScore()
        val otherScore = other.calculateScore()
        if (score > otherScore) {
            return GameResult.WIN
        }

        if (score == otherScore) {
            return GameResult.TIE
        }

        return GameResult.LOSE
    }
}

class Stay(
    cards: Cards,
) : Finished(cards) {
    override fun profitMultiple(): BigDecimal = BigDecimal(1)
}

class Burst(
    cards: Cards,
) : Finished(cards) {
    override fun profitMultiple(): BigDecimal = BigDecimal(-1)
}

class BlackJack(
    cards: Cards,
) : Finished(cards) {
    override fun profitMultiple(): BigDecimal {
        if (cards.size == FIRST_CARDS_COUNT) {
            return BigDecimal(1.5)
        }

        return BigDecimal(1)
    }

    companion object {
        private const val FIRST_CARDS_COUNT = 2
    }
}
