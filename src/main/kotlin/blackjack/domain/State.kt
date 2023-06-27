package blackjack.domain

import java.math.BigDecimal

sealed class State(
    val cards: Cards,
) {
    abstract fun hit(card: Card): State

    abstract fun stay(): State

    abstract fun calculateProfit(betAmount: BigDecimal): Profit

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

    override fun calculateProfit(betAmount: BigDecimal): Profit {
        throw IllegalStateException("끝난 상태에서만 수익을 계산할 수 있습니다.")
    }
}

sealed class Finished(
    cards: Cards,
) : State(cards) {
    abstract fun profitRate(): BigDecimal

    override fun hit(card: Card): State {
        throw IllegalStateException("끝난 상태에서 hit 할 수 없습니다.")
    }

    override fun stay(): State {
        throw IllegalStateException("끝난 상태에서 stay 할 수 없습니다.")
    }

    override fun calculateProfit(betAmount: BigDecimal): Profit {
        return Profit(betAmount * profitRate())
    }
}

class Stay(
    cards: Cards,
) : Finished(cards) {
    override fun profitRate(): BigDecimal = BigDecimal(1)
}

class Burst(
    cards: Cards,
) : Finished(cards) {
    override fun profitRate(): BigDecimal = BigDecimal(-1)
}

class BlackJack(
    cards: Cards,
) : Finished(cards) {
    override fun profitRate(): BigDecimal = BigDecimal(1.5)
}
