package blackjack.domain.player

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.EarningResult
import blackjack.domain.Player
import kotlin.math.roundToInt

internal class Dealer : Player("딜러") {
    override val maxHitScore: Int get() = HITTABLE_MAX_SCORE

    override val visibleCards: List<Card>
        get() {
            if (this.cards.isEmpty()) {
                return listOf()
            }
            return this.cards.subList(0, 1)
        }

    fun match(customers: List<Customer>): EarningResult {
        return EarningResult(customers.map { it to match(it) }.toMap())
    }

    private fun match(customer: Customer): Int {
        if (customer.score() > BlackJackGame.MAX_SCORE) {
            return -1 * customer.betting
        }
        if (this.score() > BlackJackGame.MAX_SCORE) {
            return (customer.state.earningsRate() * customer.betting).roundToInt()
        }

        // 딜러승
        if (this.score() > customer.score()) {
            return -1 * customer.betting
        }

        // 무승부
        if (this.score() == customer.score()) {
            return 0
        }

        // 고객승
        return (customer.state.earningsRate() * customer.betting).roundToInt()
    }

    companion object {
        private const val HITTABLE_MAX_SCORE = 16
    }
}
