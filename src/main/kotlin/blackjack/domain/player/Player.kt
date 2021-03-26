package blackjack.domain.player

import blackjack.domain.MatchResult
import blackjack.domain.card.state.State

class Player(name: Name, state: State) : Participant(name, state) {

    fun match(dealer: Dealer) = when (score.compareTo(dealer.score)) {
        WIN -> MatchResult.WIN
        LOSE -> MatchResult.LOSE
        else -> MatchResult.DRAW
    }

    override fun toString(): String {
        return "Player(name=$name, state=$state)"
    }

    companion object {
        private const val WIN = 1
        private const val LOSE = -1
    }
}
