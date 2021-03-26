package blackjack.domain.player

import blackjack.domain.MatchResult
import blackjack.domain.card.state.State

class Player(name: Name, state: State) : Participant(name, state) {

    fun match(dealer: Dealer): MatchResult {
        if (dealer.isBust()) {
            return MatchResult.WIN
        }

        if (this.isBust()) {
            return MatchResult.LOSE
        }

        return when (score.compareTo(dealer.score)) {
            WIN -> MatchResult.WIN
            LOSE -> MatchResult.LOSE
            else -> MatchResult.DRAW
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
