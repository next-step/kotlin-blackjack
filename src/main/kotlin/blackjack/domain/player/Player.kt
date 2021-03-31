package blackjack.domain.player

import blackjack.domain.Money
import blackjack.domain.card.state.State

class Player(
    name: Name,
    state: State,
    private val betting: Money
) : Participant(name, state) {

    fun match(dealer: Dealer): Money {

        if (dealer.state.isBlackJack() && this.state.isBlackJack()) {
            return Money.ZERO
        }

        if (dealer.state.isBust()) {
            return betting
        }

        return when (score.compareTo(dealer.score)) {
            WIN -> state.profit(betting)
            LOSE -> betting * -1.0
            else -> Money.ZERO
        }
    }

    fun canHit() = state.isHit()

    fun stay() {
        state = state.stay()
    }

    override fun toString(): String {
        return "Player(name=$name, state=$state)"
    }

    companion object {
        private const val WIN = 1
        private const val LOSE = -1
    }
}
