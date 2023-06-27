package blackjack.domain

import java.math.BigDecimal

sealed class State(
    val cards: Cards,
) {
    abstract fun draw(card: Card): State

    abstract fun stay(): State

    fun calculateScore(): Score = cards.calculateScore()

    fun calculateProfit(betAmount: BigDecimal): Profit {
        if (this !is Finished) {
            throw IllegalStateException("끝난 상태에서만 수익을 계산할 수 있습니다.")
        }

        return Profit(betAmount * profitRate())
    }
}

class Running(
    cards: Cards,
) : State(cards) {
    override fun draw(card: Card): State {
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
}

sealed class Finished(
    cards: Cards,
) : State(cards) {
    abstract fun profitRate(): BigDecimal

    override fun draw(card: Card): State {
        throw IllegalStateException("끝난 상태에서 카드를 뽑을 수 없습니다.")
    }

    override fun stay(): State {
        throw IllegalStateException("끝난 상태에서 stay 할 수 없습니다.")
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
